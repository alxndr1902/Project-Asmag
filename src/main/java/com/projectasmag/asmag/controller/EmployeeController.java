package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.employee.EmployeeResponseDTO;
import com.projectasmag.asmag.dto.employee.UpdateEmployeeRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @GetMapping
    public List<EmployeeResponseDTO> getEmployees() {
        return null;
    }

    @GetMapping("{id}")
    public EmployeeResponseDTO getEmployee(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateResponseDTO createEmployee(@RequestBody EmployeeResponseDTO employeeResponse) {
        return null;
    }

    @PutMapping("{id}")
    public UpdateResponseDTO updateEmployee(@PathVariable String id,
                                            @RequestBody UpdateEmployeeRequestDTO updateEmployeeRequest) {
        return null;
    }

    @DeleteMapping("{id}")
    public DeleteResponseDTO deleteEmployee(@PathVariable String id) {
        return null;
    }
}
