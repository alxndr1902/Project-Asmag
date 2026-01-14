package com.projectasmag.asmag.model.company;

import com.projectasmag.asmag.model.BaseModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "company_id",nullable = false)
    private Company company;

    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    @Column(name = "phone_number", length = 20, nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "identification_number", length = 20, nullable = false, unique = true)
    private String identificationNumber;

    public Employee() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
}
