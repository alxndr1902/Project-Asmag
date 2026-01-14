package com.projectasmag.asmag.service.impl.jpa;

import com.projectasmag.asmag.constant.Message;
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
import com.projectasmag.asmag.repository.AssetRepository;
import com.projectasmag.asmag.repository.AssetStatusRepository;
import com.projectasmag.asmag.repository.AssetTypeRepository;
import com.projectasmag.asmag.repository.CompanyRepository;
import com.projectasmag.asmag.service.AssetService;
import com.projectasmag.asmag.service.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssetServiceImpl extends BaseService implements AssetService {
    private final AssetRepository assetRepository;
    private final AssetTypeRepository assetTypeRepository;
    private final AssetStatusRepository assetStatusRepository;
    private final CompanyRepository companyRepository;

    public AssetServiceImpl(AssetRepository assetRepository, AssetTypeRepository assetTypeRepository, AssetStatusRepository assetStatusRepository, CompanyRepository companyRepository) {
        this.assetRepository = assetRepository;
        this.assetTypeRepository = assetTypeRepository;
        this.assetStatusRepository = assetStatusRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<AssetResponseDTO> getAssets() {
        return assetRepository.findAll().stream().map(
                        this::mapToAssetResponseDTO)
                .toList();
    }

    @Override
    public AssetResponseDTO getAsset(String id) {
        Asset asset = assetRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Asset not found"));
        return mapToAssetResponseDTO(asset);
    }

    @Override
    public CreateResponseDTO createAsset(CreateAssetRequestDTO request) {
        Asset asset = mapToAsset(request);
        createBaseModel(asset);
        assetRepository.save(asset);
        return new CreateResponseDTO(asset.getId(), Message.CREATED.getName());
    }

    @Override
    public UpdateResponseDTO updateAsset(String id, UpdateAssetRequestDTO request) {
        Asset asset = assetRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("No Asset Found"));

        if (asset.getVersion().equals(request.getVersion())) {
            asset.setCode(request.getCode());
            asset.setName(request.getName());
            update(asset);
            assetRepository.saveAndFlush(asset);
            return new UpdateResponseDTO(asset.getVersion(), Message.UPDATED.getName());
        }
        throw new RuntimeException("Request version does not match data version");
    }

    @Override
    public DeleteResponseDTO deleteAsset(String id) {
        Asset asset = assetRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("No Asset Found"));

        assetRepository.deleteById(asset.getId());
        return new DeleteResponseDTO(Message.DELETED.name());
    }

    private AssetResponseDTO mapToAssetResponseDTO(Asset asset) {
        return new AssetResponseDTO(
                asset.getId(), asset.getCode(), asset.getName(),
                asset.getType().getName(), asset.getStatus().getName(),
                asset.getCompany().getName(), asset.getExpiredDate(),
                asset.getCreatedAt(), asset.getVersion()
        );
    }

    private Asset mapToAsset(CreateAssetRequestDTO request) {
        AssetType assetType = assetTypeRepository.findById(UUID.fromString(request.getTypeId())).orElseThrow(
                () -> new RuntimeException("No Asset Type Found")
        );
        AssetStatus assetStatus = assetStatusRepository.findById(UUID.fromString(request.getStatusId())).orElseThrow(
                () -> new RuntimeException("No Asset Status Found")
        );
        Company company = companyRepository.findById(UUID.fromString(request.getCompanyId())).orElseThrow(
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
