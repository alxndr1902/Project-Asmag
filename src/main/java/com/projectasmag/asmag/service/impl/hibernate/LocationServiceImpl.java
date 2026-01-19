package com.projectasmag.asmag.service.impl.hibernate;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dao.CompanyDao;
import com.projectasmag.asmag.dao.LocationDao;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.location.CreateLocationRequestDTO;
import com.projectasmag.asmag.dto.location.LocationResponseDTO;
import com.projectasmag.asmag.dto.location.UpdateLocationRequestDTO;
import com.projectasmag.asmag.model.company.Company;
import com.projectasmag.asmag.model.company.Location;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.LocationService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Profile("hibernate")
@Service
public class LocationServiceImpl extends BaseService implements LocationService {
    private final LocationDao locationDao;
    private final CompanyDao companyDao;

    public LocationServiceImpl(LocationDao locationDao, CompanyDao companyDao) {
        this.locationDao = locationDao;
        this.companyDao = companyDao;
    }

    @Override
    public List<LocationResponseDTO> getLocations() {
        return locationDao.findAll().stream()
                .map(this::mapToLocationResponseDTO)
                .toList();
    }

    @Override
    public LocationResponseDTO getLocation(String id) {
        Location location = locationDao.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("No Location Found")
        );
        return mapToLocationResponseDTO(location);
    }

    @Override
    @Transactional
    public CreateResponseDTO createLocation(CreateLocationRequestDTO request) {
        Company company = companyDao.findById(UUID.fromString(request.getCompanyId())).orElseThrow(
                () -> new RuntimeException("No Company Found")
        );
        Location location = new Location();
        location.setName(request.getName());
        location.setCompany(company);
        prepareCreate(location);
        locationDao.save(location);
        return new CreateResponseDTO(company.getId(), Message.CREATED.getName());
    }

    @Override
    @Transactional
    public UpdateResponseDTO updateLocation(String id, UpdateLocationRequestDTO request) {
        Location location = locationDao.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("No Location Found")
        );

        if (location.getVersion().equals(request.getVersion())) {
            location.setName(request.getName());
            prepareUpdate(location);
            locationDao.update(location);
            em.flush();
            return new UpdateResponseDTO(location.getVersion(), Message.CREATED.getName());
        }
        throw new RuntimeException("Request version does not match data version");
    }

    @Override
    @Transactional
    public DeleteResponseDTO deleteLocation(String id) {
        Location location = locationDao.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("No Location Found")
        );
        locationDao.deleteById(location.getId());
        return new DeleteResponseDTO(Message.DELETED.name());
    }

    private LocationResponseDTO mapToLocationResponseDTO(Location location) {
        return new LocationResponseDTO(location.getId(), location.getName(), location.getCompany().getName(),
                location.getVersion());
    }
}
