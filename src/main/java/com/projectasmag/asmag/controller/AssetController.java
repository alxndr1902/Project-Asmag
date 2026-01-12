package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.asset.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/assets")
public class AssetController {
    @GetMapping
    public List<AssetResponseDTO> getAssets() {
        return null;
    }

    @GetMapping("{id}")
    public AssetResponseDTO getAsset(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateResponseDTO createAsset(@RequestBody CreateAssetRequestDTO request) {
        return null;
    }

    @PutMapping("{id}")
    public UpdateResponseDTO updateAsset(@PathVariable String id,
                                         @RequestBody UpdateAssetRequestDTO request) {
        return null;
    }

    @DeleteMapping("{id}")
    public DeleteResponseDTO deleteAsset(@PathVariable String id) {
        return null;
    }

    @GetMapping("status")
    public List<AssetStatusResponseDTO> getAssetStatus() {
        return null;
    }

    @GetMapping("status/{id}")
    public AssetStatusResponseDTO getAssetStatus(@PathVariable String id) {
        return null;
    }

    @GetMapping("types")
    public List<AssetTypeResponseDTO> getAssetTypes() {
        return null;
    }

    @GetMapping("types/{id}")
    public AssetTypeResponseDTO getAssetType(@PathVariable String id) {
        return null;
    }
}
