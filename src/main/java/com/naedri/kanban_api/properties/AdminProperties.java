package com.naedri.kanban_api.properties;

import com.naedri.kanban_api.domain.enums.RoleName;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@ConfigurationProperties(prefix = "spring.security.user")
public class AdminProperties {

    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final List<RoleName> roles;

    public AdminProperties(String email, String password, String firstName, String lastName) {
        this.email = email != null
                ? email
                : "admin@kanban.local";
        this.password = password != null
                ? password
                : "admin_pwd_changemeinprod";
        this.firstName = firstName != null
                ? firstName
                : "John";
        this.lastName = lastName != null
                ? lastName
                : "Admin";

        this.roles = List.of(RoleName.ROLE_ADMIN);

    }
}
