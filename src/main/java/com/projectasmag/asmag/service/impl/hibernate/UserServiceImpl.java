package com.projectasmag.asmag.service.impl.hibernate;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dao.EmployeeDao;
import com.projectasmag.asmag.dao.RoleDao;
import com.projectasmag.asmag.dao.UserDao;
import com.projectasmag.asmag.dto.CreateResponseDTO;
import com.projectasmag.asmag.dto.DeleteResponseDTO;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.user.RegisterRequestDTO;
import com.projectasmag.asmag.dto.user.ChangePasswordRequestDTO;
import com.projectasmag.asmag.dto.user.UpdateUserRequestDTO;
import com.projectasmag.asmag.dto.user.UserResponseDTO;
import com.projectasmag.asmag.model.company.Employee;
import com.projectasmag.asmag.model.company.Role;
import com.projectasmag.asmag.model.company.User;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile("hibernate")
@Service
public class UserServiceImpl extends BaseService implements UserService{
    private final UserDao userDao;
    private final EmployeeDao employeeDao;
    private final RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, EmployeeDao employeeDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.employeeDao = employeeDao;
        this.roleDao = roleDao;
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return userDao.findAll().stream()
                .map(this::mapToUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDTO getUser(String id) {
        Optional<User> user = userDao.findById(UUID.fromString(id));
        if (user.isPresent()) {
            User existingUser = user.get();
            return mapToUserResponseDto(existingUser);
        }
        return null;
    }

    @Transactional
    @Override
    public UpdateResponseDTO updateUser(String id, UpdateUserRequestDTO request) {
        User user = userDao.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("User Not Found")
        );

        user.setEmail(request.getEmail());
        prepareUpdate(user);
        userDao.update(user);
        em.flush();
        return new UpdateResponseDTO(user.getVersion(), Message.UPDATED.getName());
    }

    @Transactional
    @Override
    public CreateResponseDTO register(RegisterRequestDTO request) {
        User user = mapToUser(request);
        prepareCreate(user);
        userDao.insert(user);
        return new CreateResponseDTO(user.getId(), Message.CREATED.getName());
    }

    @Transactional
    @Override
    public UpdateResponseDTO changePassword(ChangePasswordRequestDTO request) {
        return null;
    }

    @Transactional
    @Override
    public DeleteResponseDTO deleteUser(String id) {
        User user = userDao.findById(UUID.fromString(id)).orElseThrow(
                () -> new RuntimeException("No User Found")
        );
        userDao.deleteById(user.getId());
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
        Employee employee = employeeDao.findById(UUID.fromString(registerRequestDTO.getEmployeeId())).orElseThrow(
                () -> new RuntimeException("No Employee Found")
        );
        Role role = roleDao.findById(UUID.fromString(registerRequestDTO.getRoleId())).orElseThrow(
                () -> new RuntimeException("No Role Found")
        );
        User user = new User();
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(registerRequestDTO.getPassword());
        user.setEmployee(employee);
        user.setRole(role);

        return user;
    }
}
