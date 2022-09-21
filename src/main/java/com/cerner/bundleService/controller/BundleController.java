package com.cerner.bundleService.controller;

import com.cerner.bundleService.model.APIResponse;
import com.cerner.bundleService.model.Bundle;
import com.cerner.bundleService.model.BundleResponse;
import com.cerner.bundleService.repository.BundleRepository;
import com.cerner.bundleService.service.BundleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class BundleController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    BundleService bundleService;
    @Autowired
    BundleRepository bundleRepository;

    @GetMapping("/getBundleById")
    public ResponseEntity<Bundle> getBundleById(@RequestParam int bundleId){
        LOGGER.info("Get bundle with particular Id " +bundleId);
        Bundle bundle =  bundleService.getBundleById(bundleId);
        return new ResponseEntity<>(bundle, HttpStatus.OK);
    }

    @GetMapping("/bundleList")
    public BundleResponse getBundleList(){
        BundleResponse bundleResponse;
        try {
            List<Bundle> bundles = bundleService.getBundleList();
            bundleResponse = bundles.size() > 0 ?
                 BundleResponse.builder().HTTP_STATUS_CODE(HttpStatus.OK).bundlesRecieved(bundles).message("List of Bundles received are " + bundles.size()).build()
            :BundleResponse.builder().HTTP_STATUS_CODE(HttpStatus.NOT_FOUND).message("No Bundles Found").build() ;
        }catch (Exception exp){
            System.out.println(exp.getMessage());
            bundleResponse = BundleResponse.builder().HTTP_STATUS_CODE(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return bundleResponse;
    }

    @PostMapping("/addBundle")
    public Bundle addBundle(@Valid @RequestBody Bundle bundle)  {
        try {
            return bundleService.addBundle(bundle);
        }catch (Exception exceptionHandler){
            System.out.println(exceptionHandler.hashCode() + exceptionHandler.getMessage());
        }
        return bundle;
    }

    @PutMapping("/updateBundle/{bundleId}")
    public String updateBundle(@RequestBody Bundle bundle, @PathVariable int bundleId) {
        bundleService.updateBundle(bundle);
        return "Updated Successfully";
    }

    @DeleteMapping("/deleteBundle/{bundleId}")
    public String delete(@PathVariable int bundleId){
        bundleService.deleteBundle(bundleId);
        return "Record deleted Successfully";
    }
    @GetMapping("/sortingBundle/{field}")
    public APIResponse<List<Bundle>> getBundlesWithSort(@PathVariable String field){
        List<Bundle> allBundles = bundleService.findBundleWithSorting(field);

        return new APIResponse<>(allBundles.size(), allBundles);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public APIResponse<Page<Bundle>> getBundlesWithPagination(@PathVariable int offset, @PathVariable int pageSize){
        Page<Bundle> bundlesWithPagination = bundleService.findBundleWithPagination(offset, pageSize);

        return new APIResponse<>(bundlesWithPagination.getSize(), bundlesWithPagination);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    public APIResponse<Page<Bundle>> getBundlesWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        Page<Bundle> bundlesWithPaginationAndSort = bundleService.findBundleWithPaginationAndSorting(offset, pageSize, field);

        return new APIResponse<>(bundlesWithPaginationAndSort.getSize(), bundlesWithPaginationAndSort);
    }
}
