package com.projectasmag.asmag.repository;

import com.projectasmag.asmag.model.asset.AssetStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssetStatusRepository extends JpaRepository<AssetStatus, UUID> {
}
