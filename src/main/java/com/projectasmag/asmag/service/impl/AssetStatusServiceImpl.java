package com.projectasmag.asmag.service.impl;

import com.projectasmag.asmag.dto.asset.AssetStatusResponseDTO;
import com.projectasmag.asmag.exceptiohandler.exception.NotFoundException;
import com.projectasmag.asmag.model.asset.AssetStatus;
import com.projectasmag.asmag.repository.AssetStatusRepository;
import com.projectasmag.asmag.service.AssetStatusService;
import com.projectasmag.asmag.service.BaseService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AssetStatusServiceImpl extends BaseService implements AssetStatusService {
    private final AssetStatusRepository assetStatusRepository;

    protected AssetStatusServiceImpl(AssetStatusRepository assetStatusRepository) {
        this.assetStatusRepository = assetStatusRepository;
    }

    @Override
    public List<AssetStatusResponseDTO> getAssetStatus() {
        List<AssetStatus> assetStatuses = assetStatusRepository.findAll();
        List<AssetStatusResponseDTO> assetStatusResponseDTOList = new ArrayList<>();
        for (AssetStatus assetStatus : assetStatuses) {
            assetStatusResponseDTOList.add(mapToAssetStatusResponseDTO(assetStatus));
        }
        return assetStatusResponseDTOList;
    }

    @Override
    public AssetStatusResponseDTO getAssetStatus(String id) {
        UUID assetStatusId = getId(id);
        AssetStatus assetStatus = assetStatusRepository.findById(assetStatusId)
                .orElseThrow(() -> new NotFoundException("Asset Status Is Not Found"));
        return mapToAssetStatusResponseDTO(assetStatus);
    }

    private AssetStatusResponseDTO mapToAssetStatusResponseDTO(AssetStatus assetStatus) {
        AssetStatusResponseDTO responseDTO = new AssetStatusResponseDTO(
                assetStatus.getId(), assetStatus.getName());
        return responseDTO;
    }
}
