package com.cerner.bundleService.BundleControllerTest;


import com.cerner.bundleService.model.Bundle;
import com.cerner.bundleService.service.BundleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BundleControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    BundleService bundleService;

    private MockMvc mockMvc;

    @BeforeEach
    private void SetUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getBundleById_Test() throws Exception {

       Bundle bundle = new Bundle(15, "20","scopeName", "bundleAlias","bundleName","bundleSummary","stratificationCriteria","identificationCriteria","exclusionCriteria","measures","statementGroupId","measureUid","status","version",
                "author", LocalDateTime.now(),"createdBy", LocalDateTime.now(), "updatedBy");

       when(bundleService.getBundleById(15)).thenReturn(bundle);

       mockMvc.perform(get("/getBundleById")
                       .param("bundleId","15")
                       .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void bundlelist() throws Exception {
        Bundle bundle1 = new Bundle(1, "101","scopeName", "bundleAlias","bundleName","bundleSummary","stratificationCriteria","identificationCriteria","exclusionCriteria","measures","statementGroupId","measureUid","status","version",
           "author", LocalDateTime.now(),"createdBy", LocalDateTime.now(), "updatedBy");
        Bundle bundle2 = new Bundle(2, "101","scopeName", "bundleAlias","bundleName","bundleSummary","stratificationCriteria","identificationCriteria","exclusionCriteria","measures","statementGroupId","measureUid","status","version",
                "author", LocalDateTime.now(),"createdBy", LocalDateTime.now(), "updatedBy");
        List<Bundle> bundleList = new ArrayList<>();
        bundleList.add(bundle1);
        bundleList.add(bundle2);

       when(bundleService.getBundleList()).thenReturn(bundleList);

      mockMvc.perform(get("/bundleList")
                         .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }


}