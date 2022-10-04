package com.cerner.bundleService.model;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bundle_service")
@TypeDef(name="json", typeClass = JsonStringType.class)
public class Bundle {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String bundleId;
    @NotNull
    private String scopeId;
    @NotNull
    private String scopeName;
    private String bundleAlias;
    private String bundleName;
    private String bundleSummary;
    private String stratificationCriteria;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "identification_criteria_id")
    private IdentificationCriteria identificationCriteria;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "exclusion_criteria_id")
    private ExclusionCriteria exclusionCriteria;


    private String measures;
    private String statementGroupUid;
    private String measureUid;
    private String status;
  @Version
    private Long version;
    private String author;
    private LocalDateTime createdAt;
    @NotNull
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}
