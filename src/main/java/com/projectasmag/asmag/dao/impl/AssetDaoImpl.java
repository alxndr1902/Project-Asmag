package com.projectasmag.asmag.dao.impl;

import com.projectasmag.asmag.dao.AssetDao;
import com.projectasmag.asmag.model.asset.Asset;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AssetDaoImpl implements AssetDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Asset save(Asset asset) {
        em.persist(asset);
        return asset;
    }

    @Override
    public Asset update(Asset asset) {
        return em.merge(asset);
    }

    @Override
    public List<Asset> findAll() {
        return em.createQuery("SELECT a FROM Asset a", Asset.class).getResultList();
    }

    @Override
    public Optional<Asset> findById(UUID id) {
        return Optional.ofNullable(em.find(Asset.class, id));
    }

    @Override
    public void deleteById(UUID id) {
        em.remove(em.find(Asset.class, id));
    }
}
