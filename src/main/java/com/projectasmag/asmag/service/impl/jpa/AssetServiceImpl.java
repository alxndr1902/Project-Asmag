package com.projectasmag.asmag.service.impl.jpa;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.asset.AssetResponseDTO;
import com.projectasmag.asmag.dto.asset.CreateAssetRequestDTO;
import com.projectasmag.asmag.dto.asset.UpdateAssetRequestDTO;
import com.projectasmag.asmag.exceptiohandler.exception.DataNotFoundException;
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
import org.springframework.stereotype.Service;

import java.util.List;
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
        List<AssetResponseDTO> responses = assetRepository.findAll().stream()
                .map(this::mapToAssetResponseDTO)
                .toList();
        return responses;
    }

    @Override
    public AssetResponseDTO getAsset(String id) {
        UUID assetId = UUID.fromString(id);
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new DataNotFoundException("Asset Is Not Found", assetId));
        return mapToAssetResponseDTO(asset);
    }

    @Override
    public CreateResponseDTO createAsset(CreateAssetRequestDTO request) {
        UUID assetTypeId = UUID.fromString(request.getTypeId());
        AssetType assetType = assetTypeRepository.findById(assetTypeId)
                .orElseThrow(() -> new DataNotFoundException("Asset Type Is Not Found", assetTypeId));

        UUID assetStatusId = UUID.fromString(request.getStatusId());
        AssetStatus assetStatus = assetStatusRepository.findById(assetStatusId)
                .orElseThrow(() -> new DataNotFoundException("Asset Status", assetStatusId));

        UUID companyId = UUID.fromString(request.getCompanyId());
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new DataNotFoundException("Company", companyId));

        Asset asset = mapToAsset(request, assetType, assetStatus, company);
        assetRepository.save(prepareCreate(asset));
        return new CreateResponseDTO(asset.getId(), Message.CREATED.getName());
    }

    @Override
    public UpdateResponseDTO updateAsset(String id, UpdateAssetRequestDTO request) {
        UUID assetId = UUID.fromString(id);
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new DataNotFoundException("Asset Is Not Found", assetId));

        if (!asset.getVersion().equals(request.getVersion())) {
            return null;
        }

        asset.setCode(request.getCode());
        asset.setName(request.getName());
        prepareUpdate(asset);
        assetRepository.saveAndFlush(asset);
        return new UpdateResponseDTO(asset.getVersion(), Message.UPDATED.getName());
    }

    @Override
    public DeleteResponseDTO deleteAsset(String id) {
        UUID assetId = UUID.fromString(id);
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new DataNotFoundException("Asset Is Not Found", assetId));

        assetRepository.deleteById(asset.getId());
        return new DeleteResponseDTO(Message.DELETED.name());
    }

    private AssetResponseDTO mapToAssetResponseDTO(Asset asset) {
        AssetResponseDTO responseDTO = new AssetResponseDTO(
                asset.getId(), asset.getCode(), asset.getName(),
                asset.getType().getName(), asset.getStatus().getName(),
                asset.getCompany().getName(), asset.getExpiredDate(),
                asset.getCreatedAt(), asset.getVersion());
        return responseDTO;
    }

    private Asset mapToAsset(CreateAssetRequestDTO request, AssetType assetType,
                             AssetStatus assetStatus, Company company) {
        Asset asset = new Asset();
        asset.setType(assetType);
        asset.setStatus(assetStatus);
        asset.setCompany(company);
        asset.setCode(request.getCode());
        asset.setName(request.getName());
        return asset;
    }
}
