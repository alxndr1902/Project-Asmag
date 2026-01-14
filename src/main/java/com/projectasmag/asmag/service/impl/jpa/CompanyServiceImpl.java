package com.projectasmag.asmag.service.impl.jpa;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.company.CompanyResponseDTO;
import com.projectasmag.asmag.dto.company.CreateCompanyRequestDTO;
import com.projectasmag.asmag.dto.company.UpdateCompanyRequestDTO;
import com.projectasmag.asmag.model.company.Company;
import com.projectasmag.asmag.repository.CompanyRepository;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl extends BaseService implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyResponseDTO> getCompanies() {
        return companyRepository.findAll().stream()
                .map(this::mapToCompanyResponseDTO)
                .toList();
    }

    @Override
    public CompanyResponseDTO getCompany(String id) {
        Company company = companyRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Company Not Found")
        );
        return mapToCompanyResponseDTO(company);
    }

    @Override
    public CreateResponseDTO createCompany(CreateCompanyRequestDTO request) {
        Company company =  new Company();
        company.setName(request.getName());
        company.setPhoneNumber(request.getPhoneNumber());
        createBaseModel(company);
        companyRepository.save(company);
        return new CreateResponseDTO(company.getId(), Message.CREATED.getName());
    }

    @Override
    public UpdateResponseDTO updateCompany(String id, UpdateCompanyRequestDTO request) {
        Company company = companyRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("No Company Found"));
        company.setName(request.getName());
        company.setPhoneNumber(request.getPhoneNumber());
        update(company);
        companyRepository.saveAndFlush(company);
        return new UpdateResponseDTO(company.getVersion(), Message.UPDATED.getName());
    }

    private CompanyResponseDTO mapToCompanyResponseDTO(Company company) {
        return new CompanyResponseDTO(company.getId(), company.getName(), company.getPhoneNumber(),
                company.getVersion());
    }
}
