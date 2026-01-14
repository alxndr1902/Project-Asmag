package com.projectasmag.asmag.dao;

import com.projectasmag.asmag.model.loan.LoanDetail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoanDetailDao {
    LoanDetail save(LoanDetail loanDetail);

    LoanDetail update(LoanDetail loanDetail);

    List<LoanDetail> findAll();

    Optional<LoanDetail> findById(UUID id);

    void deleteById(UUID id);
}
