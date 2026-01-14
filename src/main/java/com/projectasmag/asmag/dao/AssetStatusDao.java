package com.projectasmag.asmag.dao;

import com.projectasmag.asmag.model.asset.AssetStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssetStatusDao {
    List<AssetStatus> findAll();

    Optional<AssetStatus> findById(UUID id);
}
