package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.location.LocationResponseDTO;
import com.projectasmag.asmag.dto.location.CreateLocationRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/locations")
public class LocationController {
    @GetMapping
    public List<LocationResponseDTO> getLocations() {
        return null;
    }

    @GetMapping("{id}")
    public LocationResponseDTO getLocation(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateResponseDTO createLocation(@RequestBody CreateLocationRequestDTO request) {
        return null;
    }

    @PutMapping("{id}")
    public UpdateResponseDTO updateLocation(@PathVariable String id,
                                            @RequestBody CreateLocationRequestDTO request) {
        return null;
    }

    @DeleteMapping("{id}")
    public DeleteResponseDTO deleteLocation(@PathVariable String id) {
        return null;
    }
}
