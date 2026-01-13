package com.projectasmag.asmag.model.loan;

import com.projectasmag.asmag.model.BaseModel;
import com.projectasmag.asmag.model.asset.Asset;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "loan_details")
public class LoanDetail extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    @OneToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @Column(name = "return_date")
    private LocalDateTime returnDate;
}
