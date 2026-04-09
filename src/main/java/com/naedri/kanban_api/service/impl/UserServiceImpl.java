package com.naedri.kanban_api.service.impl;

import com.naedri.kanban_api.common.util.SecurityUtils;
import com.naedri.kanban_api.domain.enums.RoleName;
import com.naedri.kanban_api.domain.model.Role;
import com.naedri.kanban_api.domain.model.User;
import com.naedri.kanban_api.dto.auth.RegisterRequest;
import com.naedri.kanban_api.mapper.RegisterMapper;
import com.naedri.kanban_api.repository.RoleRepository;
import com.naedri.kanban_api.repository.UserRepository;
import com.naedri.kanban_api.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

// ? is @NoArgsConstructor recommended for @Service
// => No, Spring prefers constructor injection

// ? is @AllArgsConstructor recommended for @Service
// => No, because it generates a constructor with ALL fields
// - Spring would inject everything (even non-dependency fields)

// ? can @RequiredArgsConstructor be used for @Service
// => Yes, Spring uses that constructor automatically
// - All instance attributes (dependencies) should be final
// @RequiredArgsConstructor

@Service
public class UserServiceImpl implements UserService {
    private static final String ERROR_MESSAGE_EMAIL_EXISTS = "Email already registered";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final RegisterMapper registerMapper;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,
                           RegisterMapper registerMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.registerMapper = registerMapper;
    }

    @Override
    public User register(RegisterRequest request) {
        String email = request.credentials().email();
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_EMAIL_EXISTS);
        }

        User user = registerMapper.toEntity(request);
        // password
        String passwordEncoded =
                passwordEncoder.encode(
                        request.credentials().password()
                );
        user.setPassword(passwordEncoded);
        // role
        Role defaultRole =
                roleRepository.findByName(RoleName.ROLE_USER)
                        .orElseThrow(() ->
                                new IllegalStateException("ROLE_USER missing in DB")
                        );
        user.setRoles(Set.of(defaultRole));

        return userRepository.save(user);

    }

    @Override
    public User getCurrentUser() {
        return SecurityUtils.getCurrentUser();
    }
}
