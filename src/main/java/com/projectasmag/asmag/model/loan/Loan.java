package com.projectasmag.asmag.model.loan;

import com.projectasmag.asmag.model.BaseModel;
import com.projectasmag.asmag.model.asset.Asset;
import com.projectasmag.asmag.model.company.Employee;
import com.projectasmag.asmag.model.company.Location;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "loans")
public class Loan extends BaseModel {
    @Column(length = 20, nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private LocalDateTime loanDate;

    @ManyToOne
    @JoinColumn(name = "asset_target_id")
    private Asset assetTarget;

    @ManyToOne
    @JoinColumn(name = "location_target_id")
    private Location locationTarget;

    @ManyToOne
    @JoinColumn(name = "employee_target_id")
    private Employee employeeTarget;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private Set<LoanDetail> loanDetails;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }

    public Asset getAssetTarget() {
        return assetTarget;
    }

    public void setAssetTarget(Asset assetTarget) {
        this.assetTarget = assetTarget;
    }

    public Location getLocationTarget() {
        return locationTarget;
    }

    public void setLocationTarget(Location locationTarget) {
        this.locationTarget = locationTarget;
    }

    public Employee getEmployeeTarget() {
        return employeeTarget;
    }

    public void setEmployeeTarget(Employee employeeTarget) {
        this.employeeTarget = employeeTarget;
    }

    public Set<LoanDetail> getLoanDetails() {
        return loanDetails;
    }

    public void setLoanDetails(Set<LoanDetail> loanDetails) {
        this.loanDetails = loanDetails;
    }
}
