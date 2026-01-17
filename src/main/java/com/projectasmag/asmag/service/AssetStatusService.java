package com.projectasmag.asmag.service;

import com.projectasmag.asmag.dto.asset.AssetStatusResponseDTO;

import java.util.List;

public interface AssetStatusService {
    List<AssetStatusResponseDTO> getAssetStatus();

    AssetStatusResponseDTO getAssetStatus(String id);
}
