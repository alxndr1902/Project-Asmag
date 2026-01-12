package com.projectasmag.asmag.controller;

import com.projectasmag.asmag.dto.CreateResponse;
import com.projectasmag.asmag.dto.DeleteResponse;
import com.projectasmag.asmag.dto.UpdateResponse;
import com.projectasmag.asmag.dto.employee.EmployeeResponse;
import com.projectasmag.asmag.dto.employee.UpdateEmployeeRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @GetMapping
    public List<EmployeeResponse> getEmployees() {
        return null;
    }

    @GetMapping("{id}")
    public EmployeeResponse getEmployee(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public CreateResponse createEmployee(@RequestBody EmployeeResponse employeeResponse) {
        return null;
    }

    @PutMapping("{id}")
    public UpdateResponse updateEmployee(@PathVariable String id,
                                         @RequestBody UpdateEmployeeRequest updateEmployeeRequest) {
        return null;
    }

    @DeleteMapping("{id}")
    public DeleteResponse deleteEmployee(@PathVariable String id) {
        return null;
    }
}
