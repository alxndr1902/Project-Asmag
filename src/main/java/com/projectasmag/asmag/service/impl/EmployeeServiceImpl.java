package com.projectasmag.asmag.service.impl;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.employee.CreateEmployeeRequestDTO;
import com.projectasmag.asmag.dto.employee.EmployeeResponseDTO;
import com.projectasmag.asmag.dto.employee.UpdateEmployeeRequestDTO;
import com.projectasmag.asmag.exceptiohandler.exception.DataIntegrationException;
import com.projectasmag.asmag.exceptiohandler.exception.DataIsNotUniqueException;
import com.projectasmag.asmag.exceptiohandler.exception.DataNotFoundException;
import com.projectasmag.asmag.model.company.Company;
import com.projectasmag.asmag.model.company.Employee;
import com.projectasmag.asmag.repository.CompanyRepository;
import com.projectasmag.asmag.repository.EmployeeRepository;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl extends BaseService implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public List<EmployeeResponseDTO> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseDTO> responseDTOs = employees.stream()
                .map(this::mapToEmployeeResponseDTO)
                .toList();
        return responseDTOs;
    }

    @Override
    public EmployeeResponseDTO getEmployee(String id) {
        UUID employeeId = UUID.fromString(id);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new DataNotFoundException("Employee Is Not Found"));
        return mapToEmployeeResponseDTO(employee);
    }

    @Override
    public CreateResponseDTO createEmployee(CreateEmployeeRequestDTO request) {
        UUID companyId = UUID.fromString(request.getCompanyId());
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new DataNotFoundException("Company Is Not Found"));

        Employee employee = new Employee();
        employee.setFullName(request.getFullName());
        employee.setCompany(company);
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setIdentificationNumber(request.getIdentificationNumber());
        prepareCreate(employee);

        employeeRepository.save(employee);
        return new CreateResponseDTO(employee.getId(), Message.CREATED.name());
    }

    @Override
    public UpdateResponseDTO updateEmployee(String id, UpdateEmployeeRequestDTO request) {
        UUID employeeId = UUID.fromString(id);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new DataNotFoundException("Employee Is Not Found"));

        if (!employee.getVersion().equals(request.getVersion())) {
            throw new DataIntegrationException("Please Refresh The Page");
        }

        if (!employee.getPhoneNumber().equals(request.getPhoneNumber())) {
            employeeRepository.findByPhoneNumber(request.getPhoneNumber())
                    .filter(existingEmployee -> !existingEmployee.getId().equals(employeeId))
                    .ifPresent(e -> {
                        throw new DataIsNotUniqueException("Phone Number Is Not Available");
                    });
        }

        employee.setFullName(request.getFullName());
        employee.setPhoneNumber(request.getPhoneNumber());
        prepareUpdate(employee);
        employeeRepository.saveAndFlush(employee);
        return new UpdateResponseDTO(employee.getVersion(), Message.UPDATED.name());
    }

    @Override
    public DeleteResponseDTO deleteEmployee(String id) {
        UUID employeeId = getId(id);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new DataNotFoundException("Employee Is Not Found"));
        employeeRepository.deleteById(employee.getId());
        return new DeleteResponseDTO(Message.DELETED.name());
    }

    private EmployeeResponseDTO mapToEmployeeResponseDTO(Employee employee) {
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO(
                employee.getId(), employee.getFullName(), employee.getPhoneNumber(),
                employee.getIdentificationNumber(), employee.getVersion());
        return responseDTO;
    }
}
