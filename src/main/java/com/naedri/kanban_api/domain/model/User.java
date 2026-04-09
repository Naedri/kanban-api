package com.naedri.kanban_api.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter

// ? why using @NoArgsConstructor for @Entity
// => Required by JPA / Hibernate:
// - Hibernate instantiates entities using reflection:
// - User user = User.class.getDeclaredConstructor().newInstance();
// - Without a no-args constructor, entity loading from DB fails.
@NoArgsConstructor

// ? why using @AllArgsConstructor for @Entity
// Not required by JPA, but useful for:
// - DTO → Entity mapping
// - manual object creation in tests
// - builder-like workflows without @Builder
@AllArgsConstructor

@ToString
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    public static User create(
            String email,
            // ? why not providing password ?
            // => Do not mix domain and security-aware logic
            // => Do not store raw/not hashed password
            String firstName,
            String lastName
    ) {
        User user = new User();

        user.email = email;
        user.firstName = firstName;
        user.lastName = lastName;

        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    // ? why not using this.email ?
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
