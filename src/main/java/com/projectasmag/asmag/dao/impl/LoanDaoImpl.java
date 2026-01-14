package com.projectasmag.asmag.dao.impl;

import com.projectasmag.asmag.dao.LoanDao;
import com.projectasmag.asmag.model.loan.Loan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LoanDaoImpl implements LoanDao{
    @PersistenceContext
    private EntityManager em;
    @Override
    public Loan save(Loan loan) {
        em.persist(loan);
        return loan;
    }

    @Override
    public Loan update(Loan loan) {
        return em.merge(loan);
    }

    @Override
    public List<Loan> findAll() {
        return em.createQuery("from Loan", Loan.class).getResultList();
    }

    @Override
    public Optional<Loan> findById(UUID id) {
        return Optional.ofNullable(em.find(Loan.class, id));
    }

    @Override
    public void deleteById(UUID id) {
        em.remove(em.find(Loan.class, id));
    }
}
