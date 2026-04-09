package com.naedri.kanban_api.config;

import com.naedri.kanban_api.domain.enums.RoleName;
import com.naedri.kanban_api.domain.model.Role;
import com.naedri.kanban_api.domain.model.User;
import com.naedri.kanban_api.properties.AdminProperties;
import com.naedri.kanban_api.repository.RoleRepository;
import com.naedri.kanban_api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminProperties adminProperties;

    public DataInitializer(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AdminProperties adminProperties
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminProperties = adminProperties;
    }

    @Override
    public void run(String... args) {

        createRoleIfNotExists(RoleName.ROLE_ADMIN);
        createRoleIfNotExists(RoleName.ROLE_USER);


        String email = adminProperties.getEmail();
        if (!userRepository.existsByEmail(email)) {

            Role adminRole =
                    roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow();

            User admin = User.create(email, adminProperties.getFirstName(), adminProperties.getLastName());

            admin.setPassword(
                    passwordEncoder.encode(
                            adminProperties.getPassword()
                    )
            );

            admin.setRoles(List.of(adminRole));

            userRepository.save(admin);
        }
    }

    private void createRoleIfNotExists(RoleName roleName) {
        roleRepository.findByName(roleName)
                .orElseGet(() ->
                        roleRepository.save(
                                Role.create(roleName)
                        )
                );
    }
}