package com.projectasmag.asmag.repository;

import com.projectasmag.asmag.model.asset.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssetRepository extends JpaRepository<Asset, UUID> {
}
