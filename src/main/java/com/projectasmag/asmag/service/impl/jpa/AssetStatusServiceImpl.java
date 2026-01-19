package com.projectasmag.asmag.service.impl.jpa;

import com.projectasmag.asmag.dto.asset.AssetStatusResponseDTO;
import com.projectasmag.asmag.model.asset.AssetStatus;
import com.projectasmag.asmag.repository.AssetStatusRepository;
import com.projectasmag.asmag.service.AssetStatusService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Profile("jpa")
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
    public AssetStatusResponseDTO getAssetStatus(String assetId) {
        AssetStatus assetStatus = assetStatusRepository.findById(UUID.fromString(assetId))
                .orElseThrow(() -> new RuntimeException("No Asset Status Found"));
        return mapToAssetStatusResponseDTO(assetStatus);
    }

    private AssetStatusResponseDTO mapToAssetStatusResponseDTO(AssetStatus assetStatus) {
        return new AssetStatusResponseDTO(
                assetStatus.getId(), assetStatus.getName()
        );
    }
}
