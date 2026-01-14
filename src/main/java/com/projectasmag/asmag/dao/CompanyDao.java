package com.projectasmag.asmag.dao;

import com.projectasmag.asmag.model.company.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyDao {
    Company save(Company company);

    Company update(Company company);

    List<Company> findAll();

    Optional<Company> findById(UUID id);

    void deleteById(UUID id);
}
