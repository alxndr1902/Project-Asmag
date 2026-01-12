package com.projectasmag.asmag.service;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.asset.AssetResponseDTO;
import com.projectasmag.asmag.dto.asset.CreateAssetRequestDTO;
import com.projectasmag.asmag.dto.asset.UpdateAssetRequestDTO;

import java.util.List;

public interface AssetService {
    List<AssetResponseDTO> getAssets();

    AssetResponseDTO getAsset(String id);

    CreateResponseDTO createAsset(CreateAssetRequestDTO request);

    UpdateResponseDTO updateAsset(UpdateAssetRequestDTO request);

    DeleteResponseDTO deleteAsset(String id);
}
