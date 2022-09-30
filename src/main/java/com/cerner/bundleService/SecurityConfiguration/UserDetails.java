package com.cerner.bundleService.SecurityConfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDetails {
    private String userName;
    private String password;
    private String role;
}
