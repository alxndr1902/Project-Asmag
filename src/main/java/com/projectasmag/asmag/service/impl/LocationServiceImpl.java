package com.projectasmag.asmag.service.impl;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.location.CreateLocationRequestDTO;
import com.projectasmag.asmag.dto.location.LocationResponseDTO;
import com.projectasmag.asmag.dto.location.UpdateLocationRequestDTO;
import com.projectasmag.asmag.exceptiohandler.exception.DataIntegrationException;
import com.projectasmag.asmag.exceptiohandler.exception.DataNotFoundException;
import com.projectasmag.asmag.model.company.Company;
import com.projectasmag.asmag.model.company.Location;
import com.projectasmag.asmag.repository.CompanyRepository;
import com.projectasmag.asmag.repository.LocationRepository;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocationServiceImpl extends BaseService implements LocationService {
    private final LocationRepository locationRepository;
    private final CompanyRepository companyRepository;

    public LocationServiceImpl(LocationRepository locationRepository, CompanyRepository companyRepository) {
        this.locationRepository = locationRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<LocationResponseDTO> getLocations() {
        List<Location> locations = locationRepository.findAll();
        List<LocationResponseDTO> responseDTOs = locations.stream()
                .map(this::mapToLocationResponseDTO)
                .toList();
        return responseDTOs;
    }

    @Override
    public LocationResponseDTO getLocation(String id) {
        UUID locationId = getId(id);
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new DataNotFoundException("Location Is Not Found"));
        return mapToLocationResponseDTO(location);
    }

    @Override
    public CreateResponseDTO createLocation(CreateLocationRequestDTO request) {
        UUID companyId = getId(request.getCompanyId());
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new DataNotFoundException("Company Is Not Found"));

        Location location = new Location();
        location.setName(request.getName());
        location.setCompany(company);
        prepareCreate(location);
        locationRepository.save(location);
        return new CreateResponseDTO(company.getId(), Message.CREATED.getName());
    }

    @Override
    public UpdateResponseDTO updateLocation(String id, UpdateLocationRequestDTO request) {
        UUID locationId = getId(id);
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new DataNotFoundException("Location Is Not Found"));

        if (!location.getVersion().equals(request.getVersion())) {
            throw new DataIntegrationException("Version Does Not Match");
        }

        location.setName(request.getName());
        prepareUpdate(location);
        locationRepository.saveAndFlush(location);
        return new UpdateResponseDTO(location.getVersion(), Message.CREATED.getName());
    }

    @Override
    public DeleteResponseDTO deleteLocation(String id) {
        UUID locationId = getId(id);
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new DataNotFoundException("Location Is Not Found"));

        locationRepository.deleteById(location.getId());
        return new DeleteResponseDTO(Message.DELETED.getName());
    }

    private LocationResponseDTO mapToLocationResponseDTO(Location location) {
        LocationResponseDTO response = new LocationResponseDTO(location.getId(), location.getName(),
                location.getCompany().getName(),
                location.getVersion());
        return response;
    }
}
