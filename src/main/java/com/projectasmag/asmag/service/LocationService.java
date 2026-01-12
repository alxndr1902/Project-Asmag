package com.projectasmag.asmag.service;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.location.CreateLocationRequestDTO;
import com.projectasmag.asmag.dto.location.LocationResponseDTO;
import com.projectasmag.asmag.dto.location.UpdateLocationRequestDTO;

import java.util.List;

public interface LocationService {
    List<LocationResponseDTO> getLocations();

    LocationResponseDTO getLocation(String id);

    CreateResponseDTO createLocation(CreateLocationRequestDTO request);

    UpdateResponseDTO updateLocation(String id, UpdateLocationRequestDTO request);

    DeleteResponseDTO deleteLocation(String id);
}
