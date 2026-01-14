package com.projectasmag.asmag.service.impl;

import com.projectasmag.asmag.dao.AssetStatusDao;
import com.projectasmag.asmag.dto.asset.AssetStatusResponseDTO;
import com.projectasmag.asmag.model.asset.AssetStatus;
import com.projectasmag.asmag.service.AssetStatusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssetStatusServiceImpl implements AssetStatusService {
    private final AssetStatusDao  assetStatusDao;

    public AssetStatusServiceImpl(AssetStatusDao assetStatusDao) {
        this.assetStatusDao = assetStatusDao;
    }

    @Override
    public List<AssetStatusResponseDTO> getAssetStatus() {
        return assetStatusDao.findAll().stream()
                .map(
                        this::mapToAssetStatusResponseDTO)
                .toList();
    }

    @Override
    public AssetStatusResponseDTO getAssetStatus(String assetId) {
        AssetStatus assetStatus = assetStatusDao.findById(UUID.fromString(assetId)).orElseThrow(
                () -> new RuntimeException("No Asset Status Found")
        );
        return mapToAssetStatusResponseDTO(assetStatus);
    }

    private AssetStatusResponseDTO mapToAssetStatusResponseDTO(AssetStatus assetStatus) {
        return new AssetStatusResponseDTO(
                assetStatus.getId(), assetStatus.getName()
        );
    }
}
