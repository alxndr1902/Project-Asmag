package com.projectasmag.asmag.repository;

import com.projectasmag.asmag.model.company.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    boolean existsByName(String name);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Company> findByName(String name);

    Optional<Company> findByPhoneNumber(String phoneNumber);
}
