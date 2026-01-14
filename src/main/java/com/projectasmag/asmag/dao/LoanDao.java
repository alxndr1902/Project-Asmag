package com.projectasmag.asmag.dao;

import com.projectasmag.asmag.model.loan.Loan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoanDao {
    Loan save(Loan loan);

    Loan update(Loan loan);

    List<Loan> findAll();

    Optional<Loan> findById(UUID id);

    void deleteById(UUID id);
}
