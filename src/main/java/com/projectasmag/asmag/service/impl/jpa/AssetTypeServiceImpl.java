package com.projectasmag.asmag.service.impl.jpa;

import com.projectasmag.asmag.dto.asset.AssetTypeResponseDTO;
import com.projectasmag.asmag.exceptiohandler.exception.DataNotFoundException;
import com.projectasmag.asmag.model.asset.AssetType;
import com.projectasmag.asmag.repository.AssetTypeRepository;
import com.projectasmag.asmag.service.AssetTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssetTypeServiceImpl implements AssetTypeService {
    private final AssetTypeRepository assetTypeRepository;

    public AssetTypeServiceImpl(AssetTypeRepository assetTypeRepository) {
        this.assetTypeRepository = assetTypeRepository;
    }


    @Override
    public List<AssetTypeResponseDTO> getAssetStatus() {
        return assetTypeRepository.findAll().stream()
                .map(
                        this::mapToAssetTypeResponseDTO)
                .toList();
    }

    @Override
    public AssetTypeResponseDTO getAssetStatus(String id) {
        UUID assetTypeId = UUID.fromString(id);
        AssetType assetType = assetTypeRepository.findById(assetTypeId)
                .orElseThrow(() -> new DataNotFoundException("Asset Type", assetTypeId));
        return mapToAssetTypeResponseDTO(assetType);
    }

    private AssetTypeResponseDTO mapToAssetTypeResponseDTO(AssetType assetType) {
        return new AssetTypeResponseDTO(assetType.getId(), assetType.getName());
    }
}
