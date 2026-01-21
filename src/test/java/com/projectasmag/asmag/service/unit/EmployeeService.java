package com.projectasmag.asmag.service.unit;

import com.projectasmag.asmag.dto.employee.CreateEmployeeRequestDTO;
import com.projectasmag.asmag.dto.employee.UpdateEmployeeRequestDTO;
import com.projectasmag.asmag.model.company.Company;
import com.projectasmag.asmag.model.company.Employee;
import com.projectasmag.asmag.repository.CompanyRepository;
import com.projectasmag.asmag.repository.EmployeeRepository;
import com.projectasmag.asmag.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class EmployeeService {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void shouldCreated_whenDataValid() {
        var employeeDto = new CreateEmployeeRequestDTO();
        employeeDto.setCompanyId("77c1050a-602e-468a-ac0b-e1295fe62693");

        var id = UUID.fromString("77c1050a-602e-468a-ac0b-e1295fe62691");

        var employeeSaved = new Employee();
        employeeSaved.setId(id);
        employeeSaved.setCreatedAt(LocalDateTime.now());
        employeeSaved.setCreatedBy(UUID.randomUUID());
        employeeSaved.setVersion(0);

        Mockito.when(companyRepository.findById(Mockito.any())).thenReturn(Optional.of(new Company()));
        Mockito.when(employeeRepository.existsByPhoneNumber(Mockito.any())).thenReturn(true);
        Mockito.when(employeeRepository.existsByIdentificationNumber(Mockito.any())).thenReturn(true);
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employeeSaved);

        var result = employeeService.createEmployee(employeeDto);

        Assertions.assertEquals(id, result.id());
        Mockito.verify(companyRepository, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(employeeRepository, Mockito.atLeast(1)).existsByPhoneNumber(Mockito.any());
        Mockito.verify(employeeRepository, Mockito.atLeast(1)).existsByIdentificationNumber(Mockito.any());
        Mockito.verify(employeeRepository, Mockito.atLeast(1)).save(Mockito.any());
    }

    @Test
    public void shouldReturnData_whenIdValid() {
        var id = UUID.randomUUID();
        var employeeSaved = new Employee();
        employeeSaved.setId(id);
        employeeSaved.setFullName("John Doe");

        Mockito.when(employeeRepository.findById(Mockito.any())).
                thenReturn(Optional.ofNullable(employeeSaved));

        var result = employeeService.getEmployee(id.toString());

        Assertions.assertEquals("John Doe", result.getFullName());
        Mockito.verify(employeeRepository, Mockito.atLeast(1))
                .findById(Mockito.any());
    }

    @Test
    public void shouldUpdateVersion_whenDataIsValid() {
        var id = UUID.randomUUID();

        var employeeDTO = new UpdateEmployeeRequestDTO();
        employeeDTO.setVersion(0);

        var savedEmployee = new Employee();
        savedEmployee.setPhoneNumber("123");
        savedEmployee.setId(id);
        savedEmployee.setVersion(1);

        Employee employeeById = new Employee();
        employeeById.setPhoneNumber("09");
        employeeById.setVersion(0);

        Mockito.when(employeeRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(employeeById));
        Mockito.when(employeeRepository.saveAndFlush(Mockito.any())).thenReturn(savedEmployee);
        Mockito.when(employeeRepository.findByPhoneNumber(Mockito.any())).thenReturn(Optional.of(savedEmployee));

        var result =  employeeService.updateEmployee(id.toString(), employeeDTO);

        Assertions.assertEquals(1, result.getVersion());


        Mockito.verify(employeeRepository, Mockito.atLeast(1))
                .findById(Mockito.any());
        Mockito.verify(employeeRepository, Mockito.atLeast(1))
                .saveAndFlush(Mockito.any());
    }
}
