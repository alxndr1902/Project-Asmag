package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.asset.AssetStatusResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("asset-status")
public class AssetStatusController {
    @GetMapping("")
    public List<AssetStatusResponseDTO> getAssetStatus() {
        return null;
    }

    @GetMapping("{id}")
    public AssetStatusResponseDTO getAssetStatus(@PathVariable String id) {
        return null;
    }

}
