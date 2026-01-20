package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.loan.CreateLoanRequestDTO;
import com.projectasmag.asmag.dto.loan.CreateLoanResponseDTO;
import com.projectasmag.asmag.dto.loan.LoanDetailResponseDTO;
import com.projectasmag.asmag.dto.loan.LoanResponseDTO;
import com.projectasmag.asmag.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> getLoans() {
        List<LoanResponseDTO> response = loanService.getLoans();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Set<LoanDetailResponseDTO>> getLoanById(@PathVariable String id) {
        Set<LoanDetailResponseDTO> response = loanService.getLoanById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateLoanResponseDTO> createLoan(@RequestBody @Valid CreateLoanRequestDTO createLoanRequest) {
        CreateLoanResponseDTO response = loanService.createLoan(createLoanRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<UpdateResponseDTO> returnAsset(@PathVariable String id,
                                                         @RequestBody @Valid List<String> assetIdList) {
        UpdateResponseDTO response = loanService.returnAsset(id, assetIdList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
