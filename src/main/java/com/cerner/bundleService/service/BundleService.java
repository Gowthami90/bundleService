package com.cerner.bundleService.service;

import com.cerner.bundleService.exception.BundleExceptionHandler;
import com.cerner.bundleService.model.Bundle;
import com.cerner.bundleService.model.Status;
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
    public Bundle getBundleById(String bundleId) throws NoSuchElementException{
        Optional<Object> bundle = bundleRepository.getBundleByBundleId(bundleId);
        if (bundleRepository.getBundleByBundleId(bundleId).isPresent()){
            return (Bundle) bundle.get();
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
       return (bundleRepository.save(bundle));
    }
    public Bundle updateBundle(Bundle bundle) {
        bundle.setUpdatedAt(LocalDateTime.now());
        bundle.setStatus(Status.PUBLISHED.getStatus());
        bundleRepository.save(bundle);
        return bundle;
    }
    public void deleteBundle(String bundleId) {
        bundleRepository.deleteById(bundleId);
    }
    public Page<Bundle> findBundleWithPaginationAndSorting(int offset, int pageSize, String field){
        Page<Bundle>  bundles = bundleRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  bundles;
    }
}
