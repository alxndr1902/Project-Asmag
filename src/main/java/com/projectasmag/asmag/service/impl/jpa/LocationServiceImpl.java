package com.projectasmag.asmag.service.impl.jpa;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.location.CreateLocationRequestDTO;
import com.projectasmag.asmag.dto.location.LocationResponseDTO;
import com.projectasmag.asmag.dto.location.UpdateLocationRequestDTO;
import com.projectasmag.asmag.exceptiohandler.exception.DataNotFoundException;
import com.projectasmag.asmag.model.company.Company;
import com.projectasmag.asmag.model.company.Location;
import com.projectasmag.asmag.repository.CompanyRepository;
import com.projectasmag.asmag.repository.LocationRepository;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.LocationService;
import jakarta.transaction.Transactional;
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
        return locationRepository.findAll().stream()
                .map(this::mapToLocationResponseDTO)
                .toList();
    }

    @Override
    public LocationResponseDTO getLocation(String id) {
        Location location = locationRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new DataNotFoundException("Location", id));
        return mapToLocationResponseDTO(location);
    }

    @Override
    public CreateResponseDTO createLocation(CreateLocationRequestDTO request) {
        Company company = companyRepository.findById(UUID.fromString(request.getCompanyId()))
                .orElseThrow(() -> new DataNotFoundException("Company", request.getCompanyId()));
        Location location = new Location();
        location.setName(request.getName());
        location.setCompany(company);
        createBaseModel(location);
        locationRepository.save(location);
        return new CreateResponseDTO(company.getId(), Message.CREATED.getName());
    }

    @Override
    public UpdateResponseDTO updateLocation(String id, UpdateLocationRequestDTO request) {
        Location location = locationRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new DataNotFoundException("Location", id));

        if (location.getVersion().equals(request.getVersion())) {
            location.setName(request.getName());
            update(location);
            locationRepository.saveAndFlush(location);
            return new UpdateResponseDTO(location.getVersion(), Message.CREATED.getName());
        }
        throw new RuntimeException("Request version does not match data version");
    }

    @Override
    public DeleteResponseDTO deleteLocation(String id) {
        Location location = locationRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new DataNotFoundException("Location", id));
        locationRepository.deleteById(location.getId());
        return new DeleteResponseDTO(Message.DELETED.name());
    }

    private LocationResponseDTO mapToLocationResponseDTO(Location location) {
        return new LocationResponseDTO(location.getId(), location.getName(), location.getCompany().getName(),
                location.getVersion());
    }
}
