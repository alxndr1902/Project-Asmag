package com.projectasmag.asmag.service;

import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.loan.CreateLoanRequestDTO;
import com.projectasmag.asmag.dto.loan.CreateLoanResponseDTO;
import com.projectasmag.asmag.dto.loan.LoanDetailResponseDTO;
import com.projectasmag.asmag.dto.loan.LoanResponseDTO;

import java.util.List;

public interface LoanService {
    List<LoanResponseDTO> getLoans();

    List<LoanDetailResponseDTO> getLoanById(String id);

    CreateLoanResponseDTO createLoan(CreateLoanRequestDTO request);

    UpdateResponseDTO returnAsset(String id, List<String> assetIdList);
}
