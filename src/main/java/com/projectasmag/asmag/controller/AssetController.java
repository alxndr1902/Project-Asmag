package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponse;
import com.projectasmag.asmag.dto.DeleteResponse;
import com.projectasmag.asmag.dto.UpdateResponse;
import com.projectasmag.asmag.dto.asset.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/assets")
public class AssetController {
    @GetMapping
    public List<AssetResponse> getAssets() {
        return null;
    }

    @GetMapping("{id}")
    public AssetResponse getAsset(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateResponse createAsset(@RequestBody CreateAssetRequest request) {
        return null;
    }

    @PutMapping("{id}")
    public UpdateResponse updateAsset(@PathVariable String id,
                                      @RequestBody UpdateAssetRequest request) {
        return null;
    }

    @DeleteMapping("{id}")
    public DeleteResponse deleteAsset(@PathVariable String id) {
        return null;
    }

    @GetMapping("status")
    public List<AssetStatusResponse> getAssetStatus() {
        return null;
    }

    @GetMapping("status/{id}")
    public AssetStatusResponse getAssetStatus(@PathVariable String id) {
        return null;
    }

    @GetMapping("types")
    public List<AssetTypeResponse> getAssetTypes() {
        return null;
    }

    @GetMapping("types/{id}")
    public AssetTypeResponse getAssetType(@PathVariable String id) {
        return null;
    }
}
