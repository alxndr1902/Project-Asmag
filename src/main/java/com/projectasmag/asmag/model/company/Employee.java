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

    @OneToOne
    private User user;
}
