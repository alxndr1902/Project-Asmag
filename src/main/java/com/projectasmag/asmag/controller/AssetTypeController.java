package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.asset.AssetTypeResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("asset-types")
public class AssetTypeController {
    @GetMapping("types")
    public List<AssetTypeResponseDTO> getAssetTypes() {
        return null;
    }

    @GetMapping("types/{id}")
    public AssetTypeResponseDTO getAssetType(@PathVariable String id) {
        return null;
    }
}
