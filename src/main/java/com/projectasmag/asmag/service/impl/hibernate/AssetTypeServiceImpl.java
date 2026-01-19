package com.projectasmag.asmag.service.impl.hibernate;

import com.projectasmag.asmag.dao.AssetTypeDao;
import com.projectasmag.asmag.dto.asset.AssetTypeResponseDTO;
import com.projectasmag.asmag.model.asset.AssetType;
import com.projectasmag.asmag.service.AssetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Profile("hibernate")
@Service
public class AssetTypeServiceImpl implements AssetTypeService {
    private final AssetTypeDao  assetTypeDao;

    public AssetTypeServiceImpl(AssetTypeDao assetTypeDao) {
        this.assetTypeDao = assetTypeDao;
    }

    @Override
    public List<AssetTypeResponseDTO> getAssetStatus() {
        return assetTypeDao.findAll().stream()
                .map(
                        this::mapToAssetTypeResponseDTO)
                .toList();
    }

    @Override
    public AssetTypeResponseDTO getAssetStatus(String assetId) {
        AssetType assetType = assetTypeDao.findById(UUID.fromString(assetId)).orElseThrow(
                () -> new RuntimeException("No Asset Type Found")
        );
        return mapToAssetTypeResponseDTO(assetType);
    }

    private AssetTypeResponseDTO mapToAssetTypeResponseDTO(AssetType assetType) {
        return new AssetTypeResponseDTO(assetType.getId(), assetType.getName());
    }
}
