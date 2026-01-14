package com.projectasmag.asmag.model.asset;

import com.projectasmag.asmag.model.BaseModel;
import com.projectasmag.asmag.model.company.Company;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assets")
public class Asset extends BaseModel {
    @Column(length = 10, nullable = false, unique = true)
    private String code;

    @Column(length = 100, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private AssetType type;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private AssetStatus status;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "expired_date")
    private LocalDateTime expiredDate;

    public Asset() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssetType getType() {
        return type;
    }

    public void setType(AssetType type) {
        this.type = type;
    }

    public AssetStatus getStatus() {
        return status;
    }

    public void setStatus(AssetStatus status) {
        this.status = status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }
}
