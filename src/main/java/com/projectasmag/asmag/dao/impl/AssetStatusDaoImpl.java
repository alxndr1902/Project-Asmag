package com.projectasmag.asmag.dao.impl;

import com.projectasmag.asmag.dao.AssetStatusDao;
import com.projectasmag.asmag.model.asset.AssetStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AssetStatusDaoImpl implements AssetStatusDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<AssetStatus> findAll() {
        return em.createQuery("from AssetStatus", AssetStatus.class).getResultList();
    }

    @Override
    public Optional<AssetStatus> findById(UUID id) {
        return Optional.ofNullable(em.find(AssetStatus.class, id));
    }
}
