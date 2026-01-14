package com.projectasmag.asmag.dao.impl;

import com.projectasmag.asmag.dao.CompanyDao;
import com.projectasmag.asmag.model.company.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CompanyDaoImpl implements CompanyDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Company save(Company company) {
        em.persist(company);
        return company;
    }

    @Override
    public Company update(Company company) {
        return em.merge(company);
    }

    @Override
    public List<Company> findAll() {
        return em.createQuery("from Company", Company.class).getResultList();
    }

    @Override
    public Optional<Company> findById(UUID id) {
        return Optional.ofNullable(em.find(Company.class, id));
    }

    @Override
    public void deleteById(UUID id) {
        em.remove(em.find(Company.class, id));
    }
}
