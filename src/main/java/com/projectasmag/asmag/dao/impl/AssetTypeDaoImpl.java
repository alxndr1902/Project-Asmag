package com.projectasmag.asmag.dao.impl;

import com.projectasmag.asmag.dao.AssetTypeDao;
import com.projectasmag.asmag.model.asset.AssetType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AssetTypeDaoImpl implements AssetTypeDao {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<AssetType> findAll() {
        return em.createQuery("SELECT at FROM AssetType at", AssetType.class).getResultList();
    }

    @Override
    public Optional<AssetType> findById(UUID id) {
        return Optional.ofNullable(em.find(AssetType.class, id));
    }
}
