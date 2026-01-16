package com.projectasmag.asmag.service.impl.jpa;

import com.projectasmag.asmag.dto.asset.AssetStatusResponseDTO;
import com.projectasmag.asmag.exceptiohandler.exception.DataNotFoundException;
import com.projectasmag.asmag.model.asset.AssetStatus;
import com.projectasmag.asmag.repository.AssetStatusRepository;
import com.projectasmag.asmag.service.AssetStatusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssetStatusServiceImpl implements AssetStatusService {
    private final AssetStatusRepository assetStatusRepository;

    public AssetStatusServiceImpl(AssetStatusRepository assetStatusRepository) {
        this.assetStatusRepository = assetStatusRepository;
    }

    @Override
    public List<AssetStatusResponseDTO> getAssetStatus() {
        return assetStatusRepository.findAll().stream()
                .map(
                        this::mapToAssetStatusResponseDTO)
                .toList();
    }

    @Override
    public AssetStatusResponseDTO getAssetStatus(String id) {
        AssetStatus assetStatus = assetStatusRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new DataNotFoundException("Asset", id));
        return mapToAssetStatusResponseDTO(assetStatus);
    }

    private AssetStatusResponseDTO mapToAssetStatusResponseDTO(AssetStatus assetStatus) {
        return new AssetStatusResponseDTO(
                assetStatus.getId(), assetStatus.getName()
        );
    }
}
