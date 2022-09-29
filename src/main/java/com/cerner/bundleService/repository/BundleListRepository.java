package com.cerner.bundleService.repository;

import com.cerner.bundleService.model.Bundle;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BundleListRepository extends PagingAndSortingRepository <Bundle, String>{
}
