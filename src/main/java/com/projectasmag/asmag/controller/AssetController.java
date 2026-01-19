package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.asset.*;
import com.projectasmag.asmag.service.AssetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/assets")
public class AssetController {
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public ResponseEntity<List<AssetResponseDTO>> getAssets() {
        List<AssetResponseDTO> response = assetService.getAssets();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AssetResponseDTO> getAsset(@PathVariable String id) {
        AssetResponseDTO response = assetService.getAsset(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDTO> createAsset(@RequestBody @Valid CreateAssetRequestDTO request) {
        CreateResponseDTO response = assetService.createAsset(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDTO> updateAsset(@PathVariable String id,
                                         @RequestBody @Valid UpdateAssetRequestDTO request) {
        UpdateResponseDTO response = assetService.updateAsset(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDTO> deleteAsset(@PathVariable String id) {
        DeleteResponseDTO response = assetService.deleteAsset(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
