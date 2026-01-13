package com.projectasmag.asmag.model.company;

import com.projectasmag.asmag.model.BaseModel;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseModel {
    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
