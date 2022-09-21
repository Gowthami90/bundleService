package com.cerner.bundleService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bundle_service")
public class Bundle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int bundleId;
    @NotNull
    private String scopeId;
    @NotNull
    private String scopeName;
    private String bundleAlias;
    private String bundleName;
    private String bundleSummary;
    private String stratificationCriteria;
    private String identificationCriteria;   // JSON
    private String exclusionCriteria;
    private String measures;
    private String statementGroupUid;
    private String measureUid;
    private String status;
    @NotNull
    private String version;
    private String author;
    private LocalDateTime createdAt;  // date time
    @NotNull
    private String createdBy;
    private LocalDateTime updatedAt;    // date time
    private String updatedBy;
}
