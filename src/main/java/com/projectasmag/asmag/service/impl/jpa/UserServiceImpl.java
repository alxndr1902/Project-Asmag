package com.projectasmag.asmag.service.impl.jpa;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.user.RegisterRequestDTO;
import com.projectasmag.asmag.dto.user.ChangePasswordRequestDTO;
import com.projectasmag.asmag.dto.user.UpdateUserRequestDTO;
import com.projectasmag.asmag.dto.user.UserResponseDTO;
import com.projectasmag.asmag.exceptiohandler.exception.DataIntegrationException;
import com.projectasmag.asmag.exceptiohandler.exception.DataIsNotUniqueException;
import com.projectasmag.asmag.exceptiohandler.exception.DataNotFoundException;
import com.projectasmag.asmag.model.company.Employee;
import com.projectasmag.asmag.model.company.Role;
import com.projectasmag.asmag.model.company.User;
import com.projectasmag.asmag.repository.EmployeeRepository;
import com.projectasmag.asmag.repository.RoleRepository;
import com.projectasmag.asmag.repository.UserRepository;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends BaseService implements UserService{
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository,
                           EmployeeRepository employeeRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> responseDTOs = users.stream()
                .map(this::mapToUserResponseDto)
                .toList();
        return responseDTOs;
    }

    @Override
    public UserResponseDTO getUser(String id) {
        UUID userId = getId(id);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User Is Not Found"));
        return mapToUserResponseDto(user);
    }

    @Override
    public UpdateResponseDTO updateUser(String id, UpdateUserRequestDTO request) {
        UUID userId = getId(id);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User Is Not Found"));

        if (!user.getVersion().equals(request.getVersion())) {
            throw new DataIntegrationException("Please Refresh The Page");
        }

        if (!user.getEmail().equals(request.getEmail())) {
            userRepository.findByEmail(request.getEmail())
                    .filter(existingUser -> !existingUser.getId().equals(userId))
                    .ifPresent(c -> {
                        throw new DataIsNotUniqueException("Email Is Not Available");
                    });
        }

        user.setEmail(request.getEmail());
        prepareUpdate(user);
        userRepository.saveAndFlush(user);
        return new UpdateResponseDTO(user.getVersion(), Message.UPDATED.getName());
    }

    @Override
    public CreateResponseDTO register(RegisterRequestDTO request) {
        UUID employeeId = getId(request.getEmployeeId());
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new DataNotFoundException("Employee Is Not Found"));

        UUID roleId = getId(request.getRoleId());
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new DataNotFoundException("Role Is Not Found"));

        if (!userRepository.existsByEmail(request.getEmail())) {
            throw new DataIsNotUniqueException("Email Is Not Available");
        }

        User user = mapToUser(request, employee, role);
        prepareCreate(user);
        userRepository.save(user);
        return new CreateResponseDTO(user.getId(), Message.CREATED.getName());
    }

    @Override
    public UpdateResponseDTO changePassword(ChangePasswordRequestDTO request) {
        return null;
    }

    @Override
    public DeleteResponseDTO deleteUser(String id) {
        UUID userId = getId(id);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User Is Not Found"));
        userRepository.deleteById(user.getId());
        return new DeleteResponseDTO("deleted");
    }

    private UserResponseDTO mapToUserResponseDto(User user) {
        UserResponseDTO response = new UserResponseDTO(user.getId(),
                user.getEmail(),
                user.getEmployee().getFullName(),
                user.getEmployee().getPhoneNumber(),
                user.getRole().getName(), user.getVersion());
        return response;
    }

    private User mapToUser(RegisterRequestDTO request, Employee employee, Role role) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setEmployee(employee);
        user.setRole(role);

        return user;
    }
}
