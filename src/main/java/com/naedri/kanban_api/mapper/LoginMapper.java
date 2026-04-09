package com.naedri.kanban_api.mapper;

import com.naedri.kanban_api.dto.auth.AuthResponse;
import com.naedri.kanban_api.dto.auth.LoginRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface LoginMapper {
    // ? why not mapping to User ?
    // User toEntity(LoginRequest dto);
    // => The following should be forbidden because a login, does not create an User, but allow authenticating it.

    UsernamePasswordAuthenticationToken toAuthentication(LoginRequest dto);

    // ? why not using User ?
    // AuthResponse toDto(User user);
    // => The service will manipulate token from JwtService.generateToker() and not user directly

    AuthResponse toDto(String token);
}
