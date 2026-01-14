package com.projectasmag.asmag.model.company;

import com.projectasmag.asmag.model.BaseModel;
import com.projectasmag.asmag.model.asset.Asset;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "companies")
public class Company extends BaseModel {
    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String phoneNumber;

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
