package com.projectasmag.asmag.service.impl.jpa;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.employee.CreateEmployeeRequestDTO;
import com.projectasmag.asmag.dto.employee.EmployeeResponseDTO;
import com.projectasmag.asmag.dto.employee.UpdateEmployeeRequestDTO;
import com.projectasmag.asmag.model.company.Company;
import com.projectasmag.asmag.model.company.Employee;
import com.projectasmag.asmag.repository.CompanyRepository;
import com.projectasmag.asmag.repository.EmployeeRepository;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.EmployeeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Profile("jpa")
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
        return employeeRepository.findAll().stream()
                .map(this::mapToEmployeeResponseDTO)
                .toList();
    }

    @Override
    public EmployeeResponseDTO getEmployee(String id) {
        Employee employee = employeeRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("No Employee Found"));
        return mapToEmployeeResponseDTO(employee);
    }

    @Override
    public CreateResponseDTO createEmployee(CreateEmployeeRequestDTO request) {
        Company company = companyRepository.findById(UUID.fromString(request.getCompanyId()))
                .orElseThrow(() -> new RuntimeException("No Employee Found"));
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
        Employee employee = employeeRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("No Employee Found"));
        if (employee.getVersion().equals(request.getVersion())) {
            employee.setFullName(request.getFullName());
            employee.setPhoneNumber(request.getPhoneNumber());
            prepareUpdate(employee);
            employeeRepository.saveAndFlush(employee);
            return new UpdateResponseDTO(employee.getVersion(), Message.UPDATED.name());
        } else {
        throw new RuntimeException("Version Mismatch");
        }
    }

    @Override
    public DeleteResponseDTO deleteEmployee(String id) {
        Employee employee = employeeRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("No Employee Found"));
        employeeRepository.deleteById(employee.getId());
        return new DeleteResponseDTO(Message.DELETED.name());
    }

    private EmployeeResponseDTO mapToEmployeeResponseDTO(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId(), employee.getFullName(), employee.getPhoneNumber(),
                employee.getIdentificationNumber(), employee.getVersion()
        );
    }
}
