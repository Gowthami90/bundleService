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
import org.springframework.http.HttpHeaders;
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

    @GetMapping("/bundles/{bundleId}")
    public ResponseEntity<Bundle> getBundleById(@PathVariable String bundleId){
        LOGGER.info("Get bundle with particular Id " +bundleId);
        Bundle bundle = (Bundle) bundleService.getBundleById(bundleId);
        return new ResponseEntity<>(bundle, HttpStatus.OK);
    }

    @GetMapping("/bundle")
    public BundleResponse getBundleList(){
        BundleResponse bundleResponse;
        try {
            List<Bundle> bundles = bundleService.getBundleList();
            bundleResponse = bundles.size() > 0 ?
                 BundleResponse.builder().HTTP_STATUS_CODE(HttpStatus.OK).bundlesRecieved(bundles).message("List of Bundles received are " + bundles.size()).build()
            :BundleResponse.builder().HTTP_STATUS_CODE(HttpStatus.NOT_FOUND).message("No Bundles Found").build() ;
        }catch (Exception exp){
            bundleResponse = BundleResponse.builder().HTTP_STATUS_CODE(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return bundleResponse;
    }

    @PostMapping("/bundles")
    public Bundle addBundle(@Valid @RequestBody Bundle bundle)  {
        try {
            return bundleService.addBundle(bundle);
        }catch (Exception exceptionHandler){
            System.out.println(exceptionHandler.hashCode() + exceptionHandler.getMessage());
        }
        return bundle;
    }

    @PutMapping("/bundles/{bundleId}")
    public Bundle updateBundle(@RequestBody Bundle bundle, @PathVariable String bundleId) {
        bundleService.updateBundle(bundle);
        return bundle;
    }
    @DeleteMapping("/bundles/{bundleId}")
    public String delete(@PathVariable String bundleId){
        bundleService.deleteBundle(bundleId);
        return "Record deleted Successfully";
    }
    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    public APIResponse<Page<Bundle>> getBundlesWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        Page<Bundle> bundlesWithPaginationAndSort = bundleService.findBundleWithPaginationAndSorting(offset, pageSize, field);

        return new APIResponse<>(bundlesWithPaginationAndSort.getSize(), bundlesWithPaginationAndSort);
    }

    @GetMapping("/bundles")
    public ResponseEntity<List<Bundle>> getAllBundles(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Bundle> list = bundleService.getAllBundles(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Bundle>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
