package com.projectasmag.asmag.dao;

import com.projectasmag.asmag.model.company.Location;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LocationDao {
    Location save(Location location);

    Location update(Location location);

    List<Location> findAll();

    Optional<Location> findById(UUID id);

    void deleteById(UUID id);
}
