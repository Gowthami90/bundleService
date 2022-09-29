package com.cerner.bundleService.service;

import com.cerner.bundleService.exception.BundleExceptionHandler;
import com.cerner.bundleService.model.Bundle;
import com.cerner.bundleService.model.StatementGroup;
import com.cerner.bundleService.model.Status;
import com.cerner.bundleService.repository.BundleListRepository;
import com.cerner.bundleService.repository.BundleRepository;
import com.cerner.bundleService.repository.StatementGroupRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class BundleService {
    @Autowired
    BundleRepository bundleRepository;

    @Autowired
    StatementGroupRepository statementGroupRepository;
    @Autowired
    BundleListRepository bundleListRepository;

    public Bundle getBundleById(String bundleId) throws NoSuchElementException{
        Optional<Object> bundle = bundleRepository.getBundleByBundleId(bundleId);
        if (bundle.isPresent()){
            return (Bundle) bundle.get();
        }else{
            throw new BundleExceptionHandler("Bundle Id not found in the database");
        }
    }
    public List<Bundle> getBundleList() {

        return bundleRepository.findAll();
    }
    public Bundle addBundle(Bundle bundle) throws JsonProcessingException {
        //Fetch statement data
        if(!bundle.getStatementGroupUid().isEmpty()){
            StatementGroup statementGroup = statementGroupRepository.findById(bundle.getStatementGroupUid()).get();

            bundle.setIdentificationCriteria(new ObjectMapper().writeValueAsString(statementGroup));
            bundle.setExclusionCriteria(new ObjectMapper().writeValueAsString(statementGroup));
        }
        bundle.setCreatedAt(LocalDateTime.now());
       return (bundleRepository.save(bundle));
    }
    public Bundle updateBundle(Bundle bundle) {
        bundle.setUpdatedAt(LocalDateTime.now());
        bundle.setStatus(Status.PUBLISHED.getStatus());
        bundleRepository.save(bundle);
        return bundle;
    }
    public void deleteBundle(String bundleId) {
        Optional<Object> bundle = bundleRepository.getBundleByBundleId(bundleId);
        if (bundle.isPresent()){
            bundleRepository.deleteById(bundleId);
        }else{
            throw new BundleExceptionHandler("Bundle Id not found in the database");
        }
    }
    public Page<Bundle> findBundleWithPaginationAndSorting(int offset, int pageSize, String field){
        Page<Bundle>  bundles = bundleRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  bundles;
    }

    public List<Bundle> getAllBundles(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Bundle> pagedResult = bundleListRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Bundle>();
        }
    }
}
