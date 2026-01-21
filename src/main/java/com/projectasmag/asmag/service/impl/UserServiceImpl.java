package com.projectasmag.asmag.service.impl;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.user.RegisterRequestDTO;
import com.projectasmag.asmag.dto.user.ChangePasswordRequestDTO;
import com.projectasmag.asmag.dto.user.UpdateUserRequestDTO;
import com.projectasmag.asmag.dto.user.UserResponseDTO;
import com.projectasmag.asmag.exceptiohandler.exception.DataIntegrationException;
import com.projectasmag.asmag.exceptiohandler.exception.DuplicateException;
import com.projectasmag.asmag.exceptiohandler.exception.NotFoundException;
import com.projectasmag.asmag.model.company.Employee;
import com.projectasmag.asmag.model.company.Role;
import com.projectasmag.asmag.model.company.User;
import com.projectasmag.asmag.repository.EmployeeRepository;
import com.projectasmag.asmag.repository.RoleRepository;
import com.projectasmag.asmag.repository.UserRepository;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.UserService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends BaseService implements UserService{
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    protected UserServiceImpl(UserRepository userRepository,
                              EmployeeRepository employeeRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
                .orElseThrow(() -> new NotFoundException("User Is Not Found"));
        return mapToUserResponseDto(user);
    }

    @Override
    public UpdateResponseDTO updateUser(String id, UpdateUserRequestDTO request) {
        UUID userId = getId(id);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User Is Not Found"));

        if (!user.getVersion().equals(request.getVersion())) {
            throw new DataIntegrationException("Please Refresh The Page");
        }

        if (!user.getEmail().equals(request.getEmail())) {
            userRepository.findByEmail(request.getEmail())
                    .filter(u -> !u.getId().equals(userId))
                    .ifPresent(c -> {
                        throw new DuplicateException("Email Is Not Available");
                    });
        }

        user.setEmail(request.getEmail());
        User updatedUser = userRepository.saveAndFlush(prepareUpdate(user));
        return new UpdateResponseDTO(updatedUser.getVersion(), Message.UPDATED.getName());
    }

    @Override
    public CreateResponseDTO register(RegisterRequestDTO request) {
        UUID employeeId = getId(request.getEmployeeId());
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee Is Not Found"));

        UUID roleId = getId(request.getRoleId());
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("Role Is Not Found"));

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateException("Email Is Not Available");
        }

        User user = mapToUser(request, employee, role);
        User savedUser = userRepository.save(prepareCreate(user));
        return new CreateResponseDTO(savedUser.getId(), Message.CREATED.getName());
    }

    @Override
    public UpdateResponseDTO changePassword(ChangePasswordRequestDTO request) {
        return null;
    }

    @Override
    public DeleteResponseDTO deleteUser(String id) {
        UUID userId = getId(id);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User Is Not Found"));
        userRepository.deleteById(user.getId());
        return new DeleteResponseDTO("deleted");
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User Not Found"));
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

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmployee(employee);
        user.setRole(role);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException(email));
        return new org.springframework.security.core.userdetails.User(
                email, user.getPassword(), new ArrayList<>());
    }
}
