package com.excelr.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CITIZEN_PLANS_INFO_NEW")
public class CitizenPlan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer citizenId;
    private String citizenName;
    private String gender;
    private String planName;
    private String planStatus;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private Double benefitAmt;
    private String denialReason;
    private LocalDate terminatedDate;
    private String terminationRsn;

    // Default constructor (required by JPA)
    public CitizenPlan() {
    }

    // Parameterized constructor (optional)
    public CitizenPlan(Integer citizenId, String citizenName, String gender, String planName, String planStatus,
                       LocalDate planStartDate, LocalDate planEndDate, Double benefitAmt, String denialReason,
                       LocalDate terminatedDate, String terminationRsn) {
        this.citizenId = citizenId;
        this.citizenName = citizenName;
        this.gender = gender;
        this.planName = planName;
        this.planStatus = planStatus;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.benefitAmt = benefitAmt;
        this.denialReason = denialReason;
        this.terminatedDate = terminatedDate;
        this.terminationRsn = terminationRsn;
    }

    // Getters and Setters
    public Integer getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Integer citizenId) {
        this.citizenId = citizenId;
    }

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public LocalDate getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(LocalDate planStartDate) {
        this.planStartDate = planStartDate;
    }

    public LocalDate getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(LocalDate planEndDate) {
        this.planEndDate = planEndDate;
    }

    public Double getBenefitAmt() {
        return benefitAmt;
    }

    public void setBenefitAmt(Double benefitAmt) {
        this.benefitAmt = benefitAmt;
    }

    public String getDenialReason() {
        return denialReason;
    }

    public void setDenialReason(String denialReason) {
        this.denialReason = denialReason;
    }

    public LocalDate getTerminatedDate() {
        return terminatedDate;
    }

    public void setTerminatedDate(LocalDate terminatedDate) {
        this.terminatedDate = terminatedDate;
    }

    public String getTerminationRsn() {
        return terminationRsn;
    }

    public void setTerminationRsn(String terminationRsn) {
        this.terminationRsn = terminationRsn;
    }

    // toString() method
    @Override
    public String toString() {
        return "CitizenPlan{" +
                "citizenId=" + citizenId +
                ", citizenName='" + citizenName + '\'' +
                ", gender='" + gender + '\'' +
                ", planName='" + planName + '\'' +
                ", planStatus='" + planStatus + '\'' +
                ", planStartDate=" + planStartDate +
                ", planEndDate=" + planEndDate +
                ", benefitAmt=" + benefitAmt +
                ", denialReason='" + denialReason + '\'' +
                ", terminatedDate=" + terminatedDate +
                ", terminationRsn='" + terminationRsn + '\'' +
                '}';
    }
}
