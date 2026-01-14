package com.projectasmag.asmag.service.impl.jpa;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.auth.RegisterRequestDTO;
import com.projectasmag.asmag.dto.user.ChangePasswordRequestDTO;
import com.projectasmag.asmag.dto.user.UpdateUserRequestDTO;
import com.projectasmag.asmag.dto.user.UserResponseDTO;
import com.projectasmag.asmag.model.company.Employee;
import com.projectasmag.asmag.model.company.Role;
import com.projectasmag.asmag.model.company.User;
import com.projectasmag.asmag.repository.EmployeeRepository;
import com.projectasmag.asmag.repository.RoleRepository;
import com.projectasmag.asmag.repository.UserRepository;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.UserService;
import jakarta.transaction.Transactional;
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
        return userRepository.findAll().stream()
                .map(this::mapToUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDTO getUser(String id) {
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToUserResponseDto(user);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDTO updateUser(String id, UpdateUserRequestDTO request) {
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setEmail(request.getEmail());
        update(user);
        userRepository.saveAndFlush(user);
        return new UpdateResponseDTO(user.getVersion(), Message.UPDATED.getName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDTO register(RegisterRequestDTO request) {
        User user = mapToUser(request);
        createBaseModel(user);
        userRepository.save(user);
        return new CreateResponseDTO(user.getId(), Message.CREATED.getName());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDTO changePassword(ChangePasswordRequestDTO request) {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DeleteResponseDTO deleteUser(String id) {
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("No User Found"));
        userRepository.deleteById(user.getId());
        return new DeleteResponseDTO("deleted");
    }

    private UserResponseDTO mapToUserResponseDto(User user) {
        return new UserResponseDTO(user.getId(),
                user.getEmail(),
                user.getEmployee().getFullName(),
                user.getEmployee().getPhoneNumber(),
                user.getRole().getName());
    }

    private User mapToUser(RegisterRequestDTO registerRequestDTO) {
        Employee employee = employeeRepository.findById(UUID.fromString(registerRequestDTO.getEmployeeId()))
                .orElseThrow(() -> new RuntimeException("No Employee Found"));

        Role role = roleRepository.findById(UUID.fromString(registerRequestDTO.getRoleId()))
                .orElseThrow(() -> new RuntimeException("No Role Found"));

        User user = new User();
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(registerRequestDTO.getPassword());
        user.setEmployee(employee);
        user.setRole(role);

        return user;
    }
}
