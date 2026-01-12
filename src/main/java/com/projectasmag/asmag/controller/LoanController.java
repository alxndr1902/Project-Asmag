package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.loan.CreateLoanRequestDTO;
import com.projectasmag.asmag.dto.loan.CreateLoanResponseDTO;
import com.projectasmag.asmag.dto.loan.LoanDetailResponseDTO;
import com.projectasmag.asmag.dto.loan.LoanResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/loans")
public class LoanController {
    @GetMapping
    public List<LoanResponseDTO> getLoans() {
        return null;
    }

    @GetMapping("{id}")
    public List<LoanDetailResponseDTO> getLoanById(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateLoanResponseDTO createLoan(@RequestBody CreateLoanRequestDTO createLoanRequest) {
        return null;
    }

    @PatchMapping("{id}/return")
    public UpdateResponseDTO returnAsset(@PathVariable String id,
                                         @RequestBody List<String> assetIdList) {
        return null;
    }
}
