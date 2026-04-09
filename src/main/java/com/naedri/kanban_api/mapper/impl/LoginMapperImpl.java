package com.naedri.kanban_api.mapper.impl;

import com.naedri.kanban_api.dto.auth.AuthResponse;
import com.naedri.kanban_api.dto.auth.LoginRequest;
import com.naedri.kanban_api.mapper.LoginMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

// ? why @Component is needed here ?
// => the class will be injected into a service constructor
// => and it is not auto-generated with Spring integration
@Component
public class LoginMapperImpl implements LoginMapper {
    @Override
    public UsernamePasswordAuthenticationToken toAuthentication(LoginRequest dto) {
        if (dto == null) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(
                dto.credentials().email(),
                dto.credentials().password()
        );
    }

    @Override
    public AuthResponse toDto(String token) {
        if (token == null) {
            return null;
        }
        return new AuthResponse(token);
    }
}
