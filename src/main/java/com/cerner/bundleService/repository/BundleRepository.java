package com.cerner.bundleService.repository;
import com.cerner.bundleService.model.Bundle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BundleRepository extends JpaRepository<Bundle, Integer>{
    public Optional<Object> getBundleByBundleId(int bundleId);
}
