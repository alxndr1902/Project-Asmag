package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.employee.CreateEmployeeRequestDTO;
import com.projectasmag.asmag.dto.employee.EmployeeResponseDTO;
import com.projectasmag.asmag.dto.employee.UpdateEmployeeRequestDTO;
import com.projectasmag.asmag.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees() {
        List<EmployeeResponseDTO> response = employeeService.getEmployees();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable String id) {
        EmployeeResponseDTO response = employeeService.getEmployee(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDTO> createEmployee(@RequestBody @Valid CreateEmployeeRequestDTO request) {
        CreateResponseDTO response = employeeService.createEmployee(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateResponseDTO> updateEmployee(@PathVariable String id,
                                                            @RequestBody @Valid UpdateEmployeeRequestDTO updateEmployeeRequest) {
        UpdateResponseDTO response = employeeService.updateEmployee(id, updateEmployeeRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteResponseDTO> deleteEmployee(@PathVariable String id) {
        DeleteResponseDTO response = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
