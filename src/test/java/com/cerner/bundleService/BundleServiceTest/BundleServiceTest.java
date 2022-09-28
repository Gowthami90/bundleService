package com.cerner.bundleService.BundleServiceTest;

import com.cerner.bundleService.model.Bundle;
import com.cerner.bundleService.repository.BundleRepository;
import com.cerner.bundleService.service.BundleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BundleServiceTest {

    @Autowired
    private BundleService bundleService;
    @MockBean
    private BundleRepository bundleRepository;

    @Test
    public void getBundleList_Test() {
        Bundle bundle1 = new Bundle("22c01569-e8fe-4b28-84dd-3fdbe31259c7", "101", "scopeName", "bundleAlias", "bundleName", "bundleSummary", "stratificationCriteria", "identificationCriteria", "exclusionCriteria", "measures", "statementGroupId", "measureUid", "status", "version",
                "author", LocalDateTime.now(), "createdBy", LocalDateTime.now(), "updatedBy");
        Bundle bundle2 = new Bundle("22c01569-e8fe-4b28-84dd-3fdbe31259c8", "102", "scopeName", "bundleAlias", "bundleName", "bundleSummary", "stratificationCriteria", "identificationCriteria", "exclusionCriteria", "measures", "statementGroupId", "measureUid", "status", "version",
                "author", LocalDateTime.now(), "createdBy", LocalDateTime.now(), "updatedBy");
        List<Bundle> bundleList = new ArrayList<>();
        bundleList.add(bundle1);
        bundleList.add(bundle2);

        when(bundleRepository.findAll()).thenReturn(bundleList);
        List<Bundle> expectedBundleList = bundleService.getBundleList();
        assertThat(expectedBundleList).isNotNull();
        assertThat(expectedBundleList.size()).isEqualTo(2);
        assertThat(expectedBundleList).isEqualTo(bundleList);
    }

    @Test
    public void postBundle_Test() {
        Bundle bundle = new Bundle("22c01569-e8fe-4b28-84dd-3fdbe31259c7", "101", "scopeName", "bundleAlias", "bundleName", "bundleSummary", "stratificationCriteria", "identificationCriteria", "exclusionCriteria", "measures", "statementGroupId", "measureUid", "status", "version",
                "author", LocalDateTime.now(), "createdBy", LocalDateTime.now(), "updatedBy");

        when(bundleRepository.save(bundle)).thenReturn(bundle);

        Bundle insertedBundle = bundleService.addBundle(bundle);

        assertThat(insertedBundle).isNotNull();
        assertThat(insertedBundle.getBundleId()).isEqualTo("22c01569-e8fe-4b28-84dd-3fdbe31259c7");
    }

    @Test
    public void getBundleById_Test() {

        Bundle bundle = new Bundle("1212d2c2-3398-497a-9f99-7754839b3720", "101", "scopeName", "bundleAlias", "bundleName", "bundleSummary", "stratificationCriteria", "identificationCriteria", "exclusionCriteria", "measures", "statementGroupId", "measureUid", "status", "version",
                "author", LocalDateTime.now(), "createdBy", LocalDateTime.now(), "updatedBy");

        when(bundleRepository.getBundleByBundleId("1212d2c2-3398-497a-9f99-7754839b3720")).thenReturn(Optional.of(bundle));

        Bundle expectedBundle = bundleService.getBundleById(bundle.getBundleId());


        assertThat(expectedBundle.getBundleId()).isEqualTo(bundle.getBundleId());
        assertThat(expectedBundle).isEqualTo(bundle);

    }

    @Test
    void updateBundle() throws Exception {
        Bundle bundle = new Bundle("22c01569-e8fe-4b28-84dd-3fdbe31259c7", "101", "scopeNameUpdated", "bundleAliasUpdated", "bundleName", "bundleSummary", "stratificationCriteria", "identificationCriteria", "exclusionCriteria", "measures", "statementGroupId", "measureUid", "status", "version",
                "author", LocalDateTime.now(), "createdBy", LocalDateTime.now(), "updatedBy");
        Bundle updateBundle = new Bundle("22c01569-e8fe-4b28-84dd-3fdbe31259c8", "102", "scopeName", "bundleAlias", "bundleName", "bundleSummary", "stratificationCriteria", "identificationCriteria", "exclusionCriteria", "measures", "statementGroupId", "measureUid", "status", "version",
                "author", LocalDateTime.now(), "createdBy", LocalDateTime.now(), "updatedBy");
        when(bundleRepository.findById(bundle.getBundleId())).thenReturn(Optional.of(bundle));
        Bundle updatedBundle =bundleService.updateBundle(updateBundle);

        assertThat(updateBundle).isEqualTo(updatedBundle);
        assertThat(updateBundle.getBundleId()).isEqualTo(updatedBundle.getBundleId());
        assertThat(updateBundle.getScopeName()).isEqualTo(updatedBundle.getScopeName());


    }

    @Test
    void getPaginationAndSortingBundleTest() {

        Bundle bundle1 = new Bundle("22c01569-e8fe-4b28-84dd-3fdbe31259c7", "101", "scopeName", "bundleAlias", "bundleName", "bundleSummary", "stratificationCriteria", "identificationCriteria", "exclusionCriteria", "measures", "statementGroupId", "measureUid", "status", "version",
                "author", LocalDateTime.now(), "createdBy", LocalDateTime.now(), "updatedBy");
        Bundle bundle2 = new Bundle("22c01569-e8fe-4b28-84dd-3fdbe31259c7", "102", "scopeName", "bundleAlias", "bundleName", "bundleSummary", "stratificationCriteria", "identificationCriteria", "exclusionCriteria", "measures", "statementGroupId", "measureUid", "status", "version",
                "author", LocalDateTime.now(), "createdBy", LocalDateTime.now(), "updatedBy");
       /* List<Bundle> bundleList = new ArrayList<>();
        bundleList.add(bundle1);/bundleList.add(bundle2);


       Pageable paging = PageRequest.of(0, 2, Sort.by("scopeId"));
      Page<Bundle> pro= Mockito.mock(Page.class);
      Mockito.when(appRepoitory.findProductByMerchantId(Mockito.anyString(), Mockito.any())).thenReturn(pro);*/

        when(bundleRepository.findAll(PageRequest.of(0, 15).withSort(Sort.by("scopeId")))).thenReturn((Page<Bundle>) bundle2);
        Bundle expectedBundle = (Bundle) bundleService.findBundleWithPaginationAndSorting(0, 10, "scopeId");
        assertThat(expectedBundle).isEqualTo(bundle2);
    }



}




