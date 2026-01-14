package com.projectasmag.asmag.repository;

import com.projectasmag.asmag.model.asset.AssetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssetTypeRepository extends JpaRepository<AssetType, UUID> {
}
