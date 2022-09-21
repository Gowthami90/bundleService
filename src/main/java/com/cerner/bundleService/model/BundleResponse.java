package com.cerner.bundleService.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Data
public class BundleResponse {
    private HttpStatus HTTP_STATUS_CODE;
    private String message;
    private List<Bundle> bundlesRecieved;
   // private Bundle bundleObjRecieved;
}
