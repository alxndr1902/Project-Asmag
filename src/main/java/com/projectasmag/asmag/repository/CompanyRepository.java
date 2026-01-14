package com.projectasmag.asmag.repository;

import com.projectasmag.asmag.model.company.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
//    @Override
//    Page<Company> findAll(Pageable pageable);
}
