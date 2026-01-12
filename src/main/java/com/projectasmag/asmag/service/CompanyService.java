package com.projectasmag.asmag.service;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.company.CompanyResponseDTO;
import com.projectasmag.asmag.dto.company.CreateCompanyRequestDTO;

import java.util.List;

public interface CompanyService {
    List<CompanyResponseDTO> getCompanies();

    CompanyResponseDTO getCompany(String id);

    CreateResponseDTO createCompany(CreateCompanyRequestDTO request);

    UpdateResponseDTO updateCompany(String id, CreateCompanyRequestDTO request);
}
