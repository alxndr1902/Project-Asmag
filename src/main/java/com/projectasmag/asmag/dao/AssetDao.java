package com.projectasmag.asmag.dao;

import com.projectasmag.asmag.model.asset.Asset;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssetDao {
    Asset save(Asset asset);

    Asset update(Asset asset);

    List<Asset> findAll();

    Optional<Asset> findById(UUID id);

    void deleteById(UUID id);
}
