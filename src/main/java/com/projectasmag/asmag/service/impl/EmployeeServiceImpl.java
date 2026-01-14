package com.projectasmag.asmag.service.impl;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dao.CompanyDao;
import com.projectasmag.asmag.dao.EmployeeDao;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.employee.CreateEmployeeRequestDTO;
import com.projectasmag.asmag.dto.employee.EmployeeResponseDTO;
import com.projectasmag.asmag.dto.employee.UpdateEmployeeRequestDTO;
import com.projectasmag.asmag.model.company.Company;
import com.projectasmag.asmag.model.company.Employee;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl extends BaseService implements EmployeeService {
    private final EmployeeDao employeeDao;
    private final CompanyDao companyDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao, CompanyDao companyDao) {
        this.employeeDao = employeeDao;
        this.companyDao = companyDao;
    }

    @Override
    public List<EmployeeResponseDTO> getEmployees() {
        return employeeDao.findAll().stream()
                .map(this::mapToEmployeeResponseDTO)
                .toList();
    }

    @Override
    public EmployeeResponseDTO getEmployee(String id) {
        Employee employee = employeeDao.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("No Employee Found")
        );
        return mapToEmployeeResponseDTO(employee);
    }

    @Override
    @Transactional
    public CreateResponseDTO createEmployee(CreateEmployeeRequestDTO request) {
        Company company = companyDao.findById(UUID.fromString(request.getCompanyId())).orElseThrow(
                () -> new RuntimeException("No Employee Found")
        );
        Employee employee = new Employee();
        employee.setFullName(request.getFullName());
        employee.setCompany(company);
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setIdentificationNumber(request.getIdentificationNumber());
        employeeDao.save(employee);
        return new CreateResponseDTO(employee.getId(), Message.CREATED.name());
    }

    @Override
    @Transactional
    public UpdateResponseDTO updateEmployee(String id, UpdateEmployeeRequestDTO request) {
        Employee employee = employeeDao.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("No Employee Found")
        );
        if (employee.getVersion().equals(request.getVersion())) {
            employee.setFullName(request.getFullName());
            employee.setPhoneNumber(request.getPhoneNumber());
            update(employee);
            employeeDao.update(employee);
            return new UpdateResponseDTO(employee.getVersion(), Message.UPDATED.name());
        } else {
        throw new RuntimeException("Version Mismatch");
        }
    }

    @Override
    @Transactional
    public DeleteResponseDTO deleteEmployee(String id) {
        Employee employee = employeeDao.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("No Employee Found")
        );
        employeeDao.deleteById(employee.getId());
        return new DeleteResponseDTO(Message.DELETED.name());
    }

    private EmployeeResponseDTO mapToEmployeeResponseDTO(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId(), employee.getFullName(), employee.getPhoneNumber(),
                employee.getIdentificationNumber()
        );
    }
}
