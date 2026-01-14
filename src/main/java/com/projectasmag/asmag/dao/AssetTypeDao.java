package com.projectasmag.asmag.dao;

import com.projectasmag.asmag.model.asset.AssetType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssetTypeDao {
    List<AssetType> findAll();

    Optional<AssetType> findById(UUID id);
}
