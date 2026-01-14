package com.projectasmag.asmag.repository;

import com.projectasmag.asmag.model.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
}
