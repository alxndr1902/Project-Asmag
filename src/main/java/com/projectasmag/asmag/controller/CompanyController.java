package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponse;
import com.projectasmag.asmag.dto.UpdateResponse;
import com.projectasmag.asmag.dto.company.CompanyResponse;
import com.projectasmag.asmag.dto.company.CreateCompanyRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companies")
public class CompanyController {
    @GetMapping
    public List<CompanyResponse> getCompanies() {
        return null;
    }

    @GetMapping("{id}")
    public CompanyResponse getCompany(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateResponse createCompany(@RequestBody CreateCompanyRequest request) {
        return null;
    }

    @PutMapping("{id}")
    public UpdateResponse updateCompany(@PathVariable String id,
                                        @RequestBody CreateCompanyRequest request) {
        return null;
    }
}
