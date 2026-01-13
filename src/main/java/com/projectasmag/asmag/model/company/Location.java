package com.projectasmag.asmag.model.company;

import com.projectasmag.asmag.model.BaseModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "locations")
public class Location extends BaseModel {
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
