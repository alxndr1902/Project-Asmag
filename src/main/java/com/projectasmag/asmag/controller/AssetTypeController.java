package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.asset.AssetTypeResponseDTO;
import com.projectasmag.asmag.service.AssetTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/asset-types")
public class AssetTypeController {
    private final AssetTypeService assetTypeService;

    public AssetTypeController(AssetTypeService assetTypeService) {
        this.assetTypeService = assetTypeService;
    }

    @GetMapping("types")
    public ResponseEntity<List<AssetTypeResponseDTO>> getAssetTypes() {
        List<AssetTypeResponseDTO> response = assetTypeService.getAssetStatus();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("types/{id}")
    public ResponseEntity<AssetTypeResponseDTO> getAssetType(@PathVariable String id) {
        AssetTypeResponseDTO response = assetTypeService.getAssetStatus(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
