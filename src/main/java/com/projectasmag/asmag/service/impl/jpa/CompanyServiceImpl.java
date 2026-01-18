package com.projectasmag.asmag.service.impl.jpa;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.company.CompanyResponseDTO;
import com.projectasmag.asmag.dto.company.CreateCompanyRequestDTO;
import com.projectasmag.asmag.dto.company.UpdateCompanyRequestDTO;
import com.projectasmag.asmag.exceptiohandler.exception.DataNotFoundException;
import com.projectasmag.asmag.model.company.Company;
import com.projectasmag.asmag.repository.CompanyRepository;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl extends BaseService implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyResponseDTO> getCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyResponseDTO> responseDTOs = companies.stream()
                .map(this::mapToCompanyResponseDTO)
                .toList();
        return responseDTOs;
    }

    @Override
    public CompanyResponseDTO getCompany(String id) {
        UUID companyId = UUID.fromString(id);
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new DataNotFoundException("Company Is Not Found", companyId));
        return mapToCompanyResponseDTO(company);
    }

    @Override
    public CreateResponseDTO createCompany(CreateCompanyRequestDTO request) {
        if (companyRepository.existsByName(request.getName())) {
            throw new RuntimeException("Company Name Already Exists");
        }

        if (companyRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Company Phone Number Already Exists");
        }

        Company company =  new Company();
        company.setName(request.getName());
        company.setPhoneNumber(request.getPhoneNumber());
        prepareCreate(company);
        companyRepository.save(company);
        return new CreateResponseDTO(company.getId(), Message.CREATED.getName());
    }

    @Override
    public UpdateResponseDTO updateCompany(String id, UpdateCompanyRequestDTO request) {
        UUID companyId = UUID.fromString(id);
        Company company = companyRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new DataNotFoundException("Company Is Not found", companyId));

        if (!company.getVersion().equals(request.getVersion())) {
            throw new RuntimeException("Version Does Not Match");
        }

        if (!company.getName().equals(request.getName())) {
            Optional<Company> existingCompany = companyRepository.findByName(request.getName());
            if (existingCompany.isPresent() && !existingCompany.get().getId().equals(companyId)) {
                throw new RuntimeException("Name Is Not Available");
            }
        }

        if (!company.getPhoneNumber().equals(request.getPhoneNumber())) {
            companyRepository.findByPhoneNumber(request.getPhoneNumber())
                    .filter(c -> !c.getId().equals(companyId))
                    .ifPresent(c -> {
                        throw new RuntimeException("Phone Number Is Not Available");
                    });
        }

        company.setName(request.getName());
        company.setPhoneNumber(request.getPhoneNumber());
        prepareUpdate(company);
        companyRepository.saveAndFlush(company);
        return new UpdateResponseDTO(company.getVersion(), Message.UPDATED.getName());
    }

    private CompanyResponseDTO mapToCompanyResponseDTO(Company company) {
        CompanyResponseDTO responseDTO = new CompanyResponseDTO(company.getId(), company.getName(), company.getPhoneNumber(),
                company.getVersion());
        return responseDTO;
    }
}
