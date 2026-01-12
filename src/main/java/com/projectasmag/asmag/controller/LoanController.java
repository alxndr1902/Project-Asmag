package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.UpdateResponse;
import com.projectasmag.asmag.dto.loan.CreateLoanRequest;
import com.projectasmag.asmag.dto.loan.CreateLoanResponse;
import com.projectasmag.asmag.dto.loan.LoanDetailResponse;
import com.projectasmag.asmag.dto.loan.LoanResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/loans")
public class LoanController {
    @GetMapping
    public List<LoanResponse> getLoans() {
        return null;
    }

    @GetMapping("{id}")
    public List<LoanDetailResponse> getLoanById(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateLoanResponse createLoan(@RequestBody CreateLoanRequest createLoanRequest) {
        return null;
    }

    @PutMapping("{id}/return")
    public UpdateResponse returnAsset(@PathVariable String id,
                                      @RequestBody List<String> assetIdList) {
        return null;
    }
}
