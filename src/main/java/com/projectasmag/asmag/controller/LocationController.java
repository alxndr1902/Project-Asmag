package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponse;
import com.projectasmag.asmag.dto.DeleteResponse;
import com.projectasmag.asmag.dto.UpdateResponse;
import com.projectasmag.asmag.dto.location.LocationResponse;
import com.projectasmag.asmag.dto.location.CreateLocationRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/locations")
public class LocationController {
    @GetMapping
    public List<LocationResponse> getLocations() {
        return null;
    }

    @GetMapping("{id}")
    public LocationResponse getLocation(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateResponse createLocation(@RequestBody CreateLocationRequest request) {
        return null;
    }

    @PutMapping("{id}")
    public UpdateResponse updateLocation(@PathVariable String id,
                                         @RequestBody CreateLocationRequest request) {
        return null;
    }

    @DeleteMapping("{id}")
    public DeleteResponse deleteLocation(@PathVariable String id) {
        return null;
    }
}
