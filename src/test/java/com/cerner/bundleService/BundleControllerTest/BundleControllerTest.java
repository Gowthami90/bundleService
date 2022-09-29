package com.cerner.bundleService.BundleControllerTest;


import com.cerner.bundleService.controller.BundleController;
import com.cerner.bundleService.model.Bundle;
import com.cerner.bundleService.service.BundleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
public class BundleControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private BundleController bundleController;

    @MockBean
    BundleService bundleService;

    private MockMvc mockMvc;

    @BeforeEach
    private void SetUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getBundleById_Test() throws Exception {

       Bundle bundle = new Bundle("eae4bba5-3aa2-4a54-a4fa-296a56878f65", "20","scopeName", "bundleAlias","bundleName","bundleSummary","stratificationCriteria","identificationCriteria","exclusionCriteria","measures","statementGroupId","measureUid","status","version",
                "author", LocalDateTime.now(),"createdBy", LocalDateTime.now(), "updatedBy");

       when(bundleService.getBundleById("eae4bba5-3aa2-4a54-a4fa-296a56878f65")).thenReturn(bundle);


        ResultActions response = mockMvc.perform(get("/bundles/{bundleId}", bundle.getBundleId()));

                 response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void bundlelist() throws Exception {
        Bundle bundle1 = new Bundle("22c01569-e8fe-4b28-84dd-3fdbe31259c7", "101","scopeName", "bundleAlias","bundleName","bundleSummary","stratificationCriteria","identificationCriteria","exclusionCriteria","measures","statementGroupId","measureUid","status","version",
           "author", LocalDateTime.now(),"createdBy", LocalDateTime.now(), "updatedBy");
        Bundle bundle2 = new Bundle("22c01569-e8fe-4b28-84dd-3fdbe31259c8", "101","scopeName", "bundleAlias","bundleName","bundleSummary","stratificationCriteria","identificationCriteria","exclusionCriteria","measures","statementGroupId","measureUid","status","version",
                "author", LocalDateTime.now(),"createdBy", LocalDateTime.now(), "updatedBy");
        List<Bundle> bundleList = new ArrayList<>();
        bundleList.add(bundle1);
        bundleList.add(bundle2);

        when(bundleService.getBundleList()).thenReturn(bundleList);

        mockMvc.perform(get("/bundle")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void addBundle_Test() throws Exception {

        Bundle bundle = new Bundle("22c01569-e8fe-4b28-84dd-3fdbe31259c7", "20", "scopeName", "bundleAlias",
                "bundleName", "bundleSummary", "stratificationCriteria",
                "identificationCriteria", "exclusionCriteria",
                "measures", "statementGroupId", "measureUid",
                "Created", "version",
                "author", LocalDateTime.now(), "Gowthami", null, "");

        String requestBody =
                "{\"scopeId\":\"22c01569-e8fe-4b28-84dd-3fdbe31259c7\"," +
                        "\"scopeName\":\"scopeName\"," +
                        "\"bundleAlias\":\"bundleAlias\"," +
                        "\"bundleName\":\"bundleName\"," +
                        "\"bundleSummary\":\"bundleSummary\"," +
                        "\"stratificationCriteria\":\"stratificationCriteria\"," +
                        "\"identificationCriteria\":\"identificationCriteria\"," +
                        "\"exclusionCriteria\":\"exclusionCriteria\"," +
                        "\"measures\":\"measures\"," +
                        "\"measureUid\":\"measureUid\"," +
                        "\"statementGroupUid\":\"statementGroupUid\"," +
                        "\"author\":\"author\"," +
                        "\"version\":\"version\"," +
                        "\"createdBy\":\"Gowthami\"}";

        when(bundleService.addBundle(bundle)).thenReturn(bundle);

        mockMvc.perform(post("/bundles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))

                .andExpect(status().isOk());
    }

    @Test
    public void updateBundle_Test() throws Exception {

        Bundle bundle = new Bundle("22c01569-e8fe-4b28-84dd-3fdbe31259c7", "20", "scopeName", "bundleAlias",
                "bundleName", "bundleSummary", "stratificationCriteria",
                "identificationCriteria", "exclusionCriteria",
                "measures", "statementGroupId", "measureUid",
                "Created", "version",
                "author", LocalDateTime.now(), "Gowthami", LocalDateTime.now(), "");

        Bundle updateBundle = new Bundle("22c01569-e8fe-4b28-84dd-3fdbe31259c7", "20", "scopeNameUpdated", "bundleAlias",
                "bundleName", "bundleSummary", "stratificationCriteria",
                "identificationCriteria", "exclusionCriteria",
                "measures", "statementGroupId", "measureUid",
                "Created", "version",
                "author", LocalDateTime.now(), "Gowthami", null, "");
        when(bundleService.getBundleById(bundle.getBundleId())).thenReturn(bundle);
       // when(bundleService.updateBundle(updateBundle)).thenReturn(updateBundle);
        Bundle updatedBundle =bundleController.updateBundle(updateBundle,updateBundle.getBundleId());


        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/bundles/{bundleId}", updateBundle.getBundleId()));

                response.andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));




    }

    @Test
    public void deleteBundle_Test() throws Exception {

        Bundle bundle = new Bundle("22c01569-e8fe-4b28-84dd-3fdbe31259c7", "20", "scopeName", "bundleAlias",
                "bundleName", "bundleSummary", "stratificationCriteria",
                "identificationCriteria", "exclusionCriteria",
                "measures", "statementGroupId", "measureUid",
                "Created", "version",
                "author", LocalDateTime.now(), "Gowthami", LocalDateTime.now(), "gowtham");

        doNothing().when(bundleService).deleteBundle("22c01569-e8fe-4b28-84dd-3fdbe31259c7");

        ResultActions response =
                mockMvc.perform(delete("/bundles/{bundleId}", bundle.getBundleId()));

        response.andExpect(status().isOk());
    }

}