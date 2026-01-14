package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.location.LocationResponseDTO;
import com.projectasmag.asmag.dto.location.CreateLocationRequestDTO;
import com.projectasmag.asmag.dto.location.UpdateLocationRequestDTO;
import com.projectasmag.asmag.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/locations")
public class LocationController {
    private final LocationService  locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<LocationResponseDTO>> getLocations() {
        List<LocationResponseDTO> response = locationService.getLocations();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<LocationResponseDTO> getLocation(@PathVariable String id) {
        LocationResponseDTO response = locationService.getLocation(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDTO> createLocation(@RequestBody CreateLocationRequestDTO request) {
        CreateResponseDTO response = locationService.createLocation(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDTO> updateLocation(@PathVariable String id,
                                            @RequestBody UpdateLocationRequestDTO request) {
        UpdateResponseDTO response = locationService.updateLocation(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDTO> deleteLocation(@PathVariable String id) {
        DeleteResponseDTO response = locationService.deleteLocation(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
