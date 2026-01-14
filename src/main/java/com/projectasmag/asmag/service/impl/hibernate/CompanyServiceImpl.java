package com.projectasmag.asmag.service.impl.hibernate;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dao.CompanyDao;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.company.CompanyResponseDTO;
import com.projectasmag.asmag.dto.company.CreateCompanyRequestDTO;
import com.projectasmag.asmag.dto.company.UpdateCompanyRequestDTO;
import com.projectasmag.asmag.model.company.Company;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

//@Service
public class CompanyServiceImpl extends BaseService implements CompanyService {
    private final CompanyDao companyDao;

    public CompanyServiceImpl(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public List<CompanyResponseDTO> getCompanies() {
        return companyDao.findAll().stream()
                .map(this::mapToCompanyResponseDTO)
                .toList();
    }

    @Override
    public CompanyResponseDTO getCompany(String id) {
        Company company = companyDao.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("Company Not Found")
        );
        return mapToCompanyResponseDTO(company);
    }

    @Override
    @Transactional
    public CreateResponseDTO createCompany(CreateCompanyRequestDTO request) {
        Company company =  new Company();
        company.setName(request.getName());
        company.setPhoneNumber(request.getPhoneNumber());
        createBaseModel(company);
        companyDao.save(company);
        return new CreateResponseDTO(company.getId(), Message.CREATED.getName());
    }

    @Override
    @Transactional
    public UpdateResponseDTO updateCompany(String id, UpdateCompanyRequestDTO request) {
        Company company = companyDao.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("No Company Found")
        );
        company.setName(request.getName());
        company.setPhoneNumber(request.getPhoneNumber());
        update(company);
        companyDao.update(company);
        em.flush();
        return new UpdateResponseDTO(company.getVersion(), Message.UPDATED.getName());
    }

    private CompanyResponseDTO mapToCompanyResponseDTO(Company company) {
        return new CompanyResponseDTO(company.getId(), company.getName(), company.getPhoneNumber()
        );
    }
}
