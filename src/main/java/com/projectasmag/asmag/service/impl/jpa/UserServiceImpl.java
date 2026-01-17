package com.projectasmag.asmag.service.impl.jpa;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.user.RegisterRequestDTO;
import com.projectasmag.asmag.dto.user.ChangePasswordRequestDTO;
import com.projectasmag.asmag.dto.user.UpdateUserRequestDTO;
import com.projectasmag.asmag.dto.user.UserResponseDTO;
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
        return userRepository.findAll().stream()
                .map(this::mapToUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDTO getUser(String id) {
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new DataNotFoundException("User", id));
        return mapToUserResponseDto(user);
    }

    @Override
    public UpdateResponseDTO updateUser(String id, UpdateUserRequestDTO request) {
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new DataNotFoundException("User", id));

        user.setEmail(request.getEmail());
        prepareUpdate(user);
        userRepository.saveAndFlush(user);
        return new UpdateResponseDTO(user.getVersion(), Message.UPDATED.getName());
    }

    @Override
    public CreateResponseDTO register(RegisterRequestDTO request) {
        User user = mapToUser(request);
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
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new DataNotFoundException("User", id));
        userRepository.deleteById(user.getId());
        return new DeleteResponseDTO("deleted");
    }

    private UserResponseDTO mapToUserResponseDto(User user) {
        return new UserResponseDTO(user.getId(),
                user.getEmail(),
                user.getEmployee().getFullName(),
                user.getEmployee().getPhoneNumber(),
                user.getRole().getName(), user.getVersion());
    }

    private User mapToUser(RegisterRequestDTO registerRequestDTO) {
        Employee employee = employeeRepository.findById(UUID.fromString(registerRequestDTO.getEmployeeId()))
                .orElseThrow(() -> new DataNotFoundException("Employee", registerRequestDTO.getEmployeeId()));

        Role role = roleRepository.findById(UUID.fromString(registerRequestDTO.getRoleId()))
                .orElseThrow(() -> new DataNotFoundException("Role", registerRequestDTO.getRoleId()));

        User user = new User();
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(registerRequestDTO.getPassword());
        user.setEmployee(employee);
        user.setRole(role);

        return user;
    }
}
