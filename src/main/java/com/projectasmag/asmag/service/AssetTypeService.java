package com.projectasmag.asmag.service;

import com.projectasmag.asmag.dto.asset.AssetTypeResponseDTO;

import java.util.List;

public interface AssetTypeService {
    List<AssetTypeResponseDTO> getAssetStatus();

    AssetTypeResponseDTO getAssetStatus(String assetId);
}
