package com.projectasmag.asmag.service.impl;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.company.CompanyResponseDTO;
import com.projectasmag.asmag.dto.company.CreateCompanyRequestDTO;
import com.projectasmag.asmag.dto.company.UpdateCompanyRequestDTO;
import com.projectasmag.asmag.exceptiohandler.exception.DataIntegrationException;
import com.projectasmag.asmag.exceptiohandler.exception.DuplicateException;
import com.projectasmag.asmag.exceptiohandler.exception.NotFoundException;
import com.projectasmag.asmag.model.company.Company;
import com.projectasmag.asmag.repository.CompanyRepository;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.CompanyService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl extends BaseService implements CompanyService {
    private final CompanyRepository companyRepository;

    protected CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Cacheable(value = "company", key = "'all'")
    @Override
    public List<CompanyResponseDTO> getCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyResponseDTO> companyResponseDTOS = new ArrayList<>();
        for (Company company : companies) {
            CompanyResponseDTO response = mapToCompanyResponseDTO(company);
            companyResponseDTOS.add(response);
        }
        return companyResponseDTOS;
    }

    @Override
    public CompanyResponseDTO getCompany(String id) {
        UUID companyId = getId(id);
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company Is Not Found"));
        return mapToCompanyResponseDTO(company);
    }

    @Override
    public CreateResponseDTO createCompany(CreateCompanyRequestDTO request) {
        if (companyRepository.existsByName(request.getName())) {
            throw new DuplicateException("Company Name Already Exists");
        }

        if (companyRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateException("Company Phone Number Already Exists");
        }

        Company company =  new Company();
        company.setName(request.getName());
        company.setPhoneNumber(request.getPhoneNumber());
        Company savedCompany = companyRepository.save(prepareCreate(company));
        return new CreateResponseDTO(savedCompany.getId(), Message.CREATED.getName());
    }

    @CacheEvict(value = "company", allEntries = true)
    @Override
    public UpdateResponseDTO updateCompany(String id, UpdateCompanyRequestDTO request) {
        UUID companyId = getId(id);
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company Is Not found"));

        if (!company.getVersion().equals(request.getVersion())) {
            throw new DataIntegrationException("Please Refresh The Page");
        }

        if (!company.getName().equals(request.getName())) {
            companyRepository.findByName(request.getName())
                    .filter(c -> !c.getId().equals(companyId))
                    .ifPresent(c -> {
                        throw new DuplicateException("Name Is Not Available");
                    });
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
        Company updatedCompany = companyRepository.saveAndFlush(prepareUpdate(company));
        return new UpdateResponseDTO(updatedCompany.getVersion(), Message.UPDATED.getName());
    }

    private CompanyResponseDTO mapToCompanyResponseDTO(Company company) {
        CompanyResponseDTO responseDTO = new CompanyResponseDTO(company.getId(), company.getName(), company.getPhoneNumber(),
                company.getVersion());
        return responseDTO;
    }
}
