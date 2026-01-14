package com.projectasmag.asmag.repository;

import com.projectasmag.asmag.model.company.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
