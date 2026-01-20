package com.projectasmag.asmag.service;

import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.loan.CreateLoanRequestDTO;
import com.projectasmag.asmag.dto.loan.CreateLoanResponseDTO;
import com.projectasmag.asmag.dto.loan.LoanDetailResponseDTO;
import com.projectasmag.asmag.dto.loan.LoanResponseDTO;

import java.util.List;
import java.util.Set;

public interface LoanService {
    List<LoanResponseDTO> getLoans();

    Set<LoanDetailResponseDTO> getLoanById(String id);

    CreateLoanResponseDTO createLoan(CreateLoanRequestDTO request);

    UpdateResponseDTO returnAsset(String id, List<String> assetIdList);
}
