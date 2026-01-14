package com.projectasmag.asmag.service;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.employee.CreateEmployeeRequestDTO;
import com.projectasmag.asmag.dto.employee.EmployeeResponseDTO;
import com.projectasmag.asmag.dto.employee.UpdateEmployeeRequestDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getEmployees();

    EmployeeResponseDTO getEmployee(String id);

    CreateResponseDTO createEmployee(CreateEmployeeRequestDTO request);

    UpdateResponseDTO updateEmployee(String id, UpdateEmployeeRequestDTO request);

    DeleteResponseDTO deleteEmployee(String id);
}
