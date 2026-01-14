package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.asset.AssetStatusResponseDTO;
import com.projectasmag.asmag.service.AssetStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/asset-status")
public class AssetStatusController {
    private final AssetStatusService assetStatusService;

    public AssetStatusController(AssetStatusService assetStatusService) {
        this.assetStatusService = assetStatusService;
    }

    @GetMapping("")
    public ResponseEntity<List<AssetStatusResponseDTO>> getAssetStatus() {
        List<AssetStatusResponseDTO> response = assetStatusService.getAssetStatus();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AssetStatusResponseDTO> getAssetStatus(@PathVariable String id) {
        AssetStatusResponseDTO response = assetStatusService.getAssetStatus(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
