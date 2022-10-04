package com.cerner.bundleService.model;

import com.cerner.bundleService.model.Bundle;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "identification_criteria")
@Component
public class IdentificationCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private String statementGroupSummary;
    @Column
    private String statementGroupDisplay;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "identificationCriteria")
    private Bundle bundle;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStatementGroupSummary() {
        return statementGroupSummary;
    }
    public void setStatementGroupSummary(String statementGroupSummary) {
        this.statementGroupSummary = statementGroupSummary;
    }
    public String getStatementGroupDisplay() {
        return statementGroupDisplay;
    }
    public void setStatementGroupDisplay(String statementGroupDisplay) {
        this.statementGroupDisplay = statementGroupDisplay;
    }
    public Bundle getBundle() {
        return bundle;
    }
    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }



}
