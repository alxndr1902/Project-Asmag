package com.projectasmag.asmag.repository;

import com.projectasmag.asmag.model.company.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
}
