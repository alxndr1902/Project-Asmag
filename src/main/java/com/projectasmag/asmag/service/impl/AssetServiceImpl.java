package com.projectasmag.asmag.service.impl;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dao.AssetDao;
import com.projectasmag.asmag.dao.AssetStatusDao;
import com.projectasmag.asmag.dao.AssetTypeDao;
import com.projectasmag.asmag.dao.CompanyDao;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.asset.AssetResponseDTO;
import com.projectasmag.asmag.dto.asset.CreateAssetRequestDTO;
import com.projectasmag.asmag.dto.asset.UpdateAssetRequestDTO;
import com.projectasmag.asmag.model.asset.Asset;
import com.projectasmag.asmag.model.asset.AssetStatus;
import com.projectasmag.asmag.model.asset.AssetType;
import com.projectasmag.asmag.model.company.Company;
import com.projectasmag.asmag.service.AssetService;
import com.projectasmag.asmag.service.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssetServiceImpl extends BaseService implements AssetService {
    private final AssetDao assetDao;
    private final AssetTypeDao assetTypeDao;
    private final AssetStatusDao assetStatusDao;
    private final CompanyDao companyDao;

    public AssetServiceImpl(AssetDao assetDao, AssetTypeDao assetTypeDao, AssetStatusDao assetStatusDao, CompanyDao companyDao) {
        this.assetDao = assetDao;
        this.assetTypeDao = assetTypeDao;
        this.assetStatusDao = assetStatusDao;
        this.companyDao = companyDao;
    }

    @Override
    public List<AssetResponseDTO> getAssets() {
        return assetDao.findAll().stream().map(
                        this::mapToAssetResponseDTO)
                .toList();
    }

    @Override
    public AssetResponseDTO getAsset(String id) {
        Optional<Asset> asset = assetDao.findById(UUID.fromString(id));
        if (asset.isPresent()) {
            Asset existingAsset = asset.get();
            return mapToAssetResponseDTO(existingAsset);
        }
        return null;
    }

    @Transactional
    @Override
    public CreateResponseDTO createAsset(CreateAssetRequestDTO request) {
        Asset asset = mapToAsset(request);
        createBaseModel(asset);
        assetDao.save(asset);
        return new CreateResponseDTO(asset.getId(), Message.CREATED.name());
    }

    @Transactional
    @Override
    public UpdateResponseDTO updateAsset(String id, UpdateAssetRequestDTO request) {
        Asset asset = assetDao.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("No Asset Found")
        );

        if (asset.getVersion().equals(request.getVersion())) {
            asset.setCode(request.getCode());
            asset.setName(request.getName());
            update(asset);
            assetDao.update(asset);
            return new UpdateResponseDTO(asset.getVersion(), Message.UPDATED.name());
        }
        throw new RuntimeException("Request version does not match data version");
    }

    @Transactional
    @Override
    public DeleteResponseDTO deleteAsset(String id) {
        Asset asset = assetDao.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("No Asset Found")
        );

        assetDao.deleteById(asset.getId());
        return new DeleteResponseDTO(Message.DELETED.name());
    }

    private AssetResponseDTO mapToAssetResponseDTO(Asset asset) {
        return new AssetResponseDTO(
                asset.getId(), asset.getCode(), asset.getName(),
                asset.getType().getName(), asset.getStatus().getName(),
                asset.getCompany().getName(), asset.getExpiredDate(),
                asset.getCreatedAt()
        );
    }

    private Asset mapToAsset(CreateAssetRequestDTO request) {
        AssetType assetType = assetTypeDao.findById(UUID.fromString(request.getTypeId())).orElseThrow(
                () -> new RuntimeException("No Asset Type Found")
        );
        AssetStatus assetStatus = assetStatusDao.findById(UUID.fromString(request.getStatusId())).orElseThrow(
                () -> new RuntimeException("No Asset Status Found")
        );
        Company company = companyDao.findById(UUID.fromString(request.getCompanyId())).orElseThrow(
                () -> new RuntimeException("No Company Found")
        );
        Asset asset = new Asset();
        asset.setType(assetType);
        asset.setStatus(assetStatus);
        asset.setCompany(company);
        asset.setCode(request.getCode());
        asset.setName(request.getName());
        return asset;
    }
}
