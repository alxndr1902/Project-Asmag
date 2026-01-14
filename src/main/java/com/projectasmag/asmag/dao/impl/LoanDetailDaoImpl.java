package com.projectasmag.asmag.dao.impl;

import com.projectasmag.asmag.dao.LoanDetailDao;
import com.projectasmag.asmag.model.loan.LoanDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LoanDetailDaoImpl implements LoanDetailDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public LoanDetail save(LoanDetail loanDetail) {
        em.persist(loanDetail);
        return loanDetail;
    }

    @Override
    public LoanDetail update(LoanDetail loanDetail) {
        return em.merge(loanDetail);
    }

    @Override
    public List<LoanDetail> findAll() {
        return em.createQuery("from LoanDetail", LoanDetail.class).getResultList();
    }

    @Override
    public Optional<LoanDetail> findById(UUID id) {
        return Optional.ofNullable(em.find(LoanDetail.class, id));
    }

    @Override
    public void deleteById(UUID id) {
        em.remove(em.find(LoanDetail.class, id));
    }
}
