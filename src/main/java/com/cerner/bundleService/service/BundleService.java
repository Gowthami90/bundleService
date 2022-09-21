package com.cerner.bundleService.service;

import com.cerner.bundleService.exception.BundleExceptionHandler;
import com.cerner.bundleService.model.Bundle;
import com.cerner.bundleService.model.StatusEnum;
import com.cerner.bundleService.repository.BundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class BundleService {
    @Autowired
    BundleRepository bundleRepository;
    public Bundle getBundleById(int bundleId) throws NoSuchElementException{
        Optional<Bundle> bundle = bundleRepository.findById(bundleId);
        if (bundleRepository.getBundleByBundleId(bundleId).isPresent()){
            return bundle.get();
        }else{
            throw new BundleExceptionHandler("Bundle Id not found in the database");
        }
    }
    public List<Bundle> getBundleList() {
        return bundleRepository.findAll();
    }
    public Bundle addBundle(Bundle bundle) {
        System.out.println("add bundle service");
        bundle.setCreatedAt(LocalDateTime.now());
        bundle.setStatus(StatusEnum.CREATED.toString());
       return (bundleRepository.save(bundle));
    }
    public void updateBundle(Bundle bundle) {
        bundle.setUpdatedAt(LocalDateTime.now());
        bundle.setStatus(StatusEnum.UPDATED.toString());
        bundleRepository.save(bundle);
   }

    public void deleteBundle(int bundleId) {
        bundleRepository.deleteById(bundleId);
    }

    public List<Bundle> findBundleWithSorting(String field){
        return bundleRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }
    public Page<Bundle> findBundleWithPagination(int offset, int pageSize){
        Page<Bundle>  bundles = bundleRepository.findAll(PageRequest.of(offset, pageSize));
        return  bundles;
    }

    public Page<Bundle> findBundleWithPaginationAndSorting(int offset, int pageSize, String field){
        Page<Bundle>  bundles = bundleRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  bundles;
    }



}
