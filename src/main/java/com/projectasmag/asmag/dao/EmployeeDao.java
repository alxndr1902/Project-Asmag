package com.projectasmag.asmag.dao;

import com.projectasmag.asmag.model.company.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeDao {
    Employee save(Employee employee);

    Employee update(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findById(UUID id);

    void deleteById(UUID id);
}
