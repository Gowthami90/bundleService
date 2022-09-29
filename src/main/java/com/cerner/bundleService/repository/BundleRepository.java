package com.cerner.bundleService.repository;
import com.cerner.bundleService.model.Bundle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BundleRepository extends JpaRepository<Bundle, String>{
    public Optional<Object> getBundleByBundleId(String bundleId);

    @Query(value = "INSERT INTO cerner.bundle_service (bundle_id,bundle_alias,bundle_name,bundle_summary,created_at,created_by,exclusion_criteria," +
            "identification_criteria,measure_uid,measures,scope_id,scope_name,statement_group_uid,status,stratification_criteria,"+
            "update_at,update_by, version)"+
            "VALUES (:bundle.getBundleId(), :bundle.getBundleAlias(), :bundle.getBundleName(), :bundle.getBundleSummary(),"+
            ":bundle.getCreatedAt(), :bundle.getCreatedBy(), :bundle.getExclusionCriteria(), :bundle.getIdentificationCriteria(),"+
            ":bundle.getMeasureUid(), :bundle.getMeasures(), :bundle.getScopeId(), :bundle.getScopeName(), :bundle.getStatementGroupUid(),"+
            ":bundle.getStatus(), :bundle.getStratificationCriteria(), :bundle.getUpdatedAt(), :bundle.getUpdatedBy(), :bundle.getVersion()"
        , nativeQuery = true)

    public Optional<Object> insertBundleData(Bundle bundle);


}
