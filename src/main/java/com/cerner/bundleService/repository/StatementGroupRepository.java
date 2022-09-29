package com.cerner.bundleService.repository;
import com.cerner.bundleService.model.Bundle;
import com.cerner.bundleService.model.StatementGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatementGroupRepository extends JpaRepository<StatementGroup, String>{

}
