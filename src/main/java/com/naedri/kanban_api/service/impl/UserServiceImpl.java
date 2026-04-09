package com.naedri.kanban_api.service.impl;

import com.naedri.kanban_api.common.util.SecurityUtils;
import com.naedri.kanban_api.domain.model.User;
import com.naedri.kanban_api.dto.auth.RegisterRequest;
import com.naedri.kanban_api.mapper.RegisterMapper;
import com.naedri.kanban_api.repository.UserRepository;
import com.naedri.kanban_api.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private final RegisterMapper registerMapper;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RegisterMapper registerMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        return userRepository.save(user);

    }

    @Override
    public User getCurrentUser() {
        return SecurityUtils.getCurrentUser();
    }
}
