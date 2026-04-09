package com.naedri.kanban_api.mapper.impl;

import com.naedri.kanban_api.domain.model.User;
import com.naedri.kanban_api.dto.auth.AuthResponse;
import com.naedri.kanban_api.dto.auth.RegisterRequest;
import com.naedri.kanban_api.mapper.RegisterMapper;
import org.springframework.stereotype.Component;

// ? why @Component is needed here ?
// => the class will be injected into a service constructor
// => and it is not auto-generated with Spring integration
@Component
public class RegisterMapperImpl implements RegisterMapper {
    @Override
    public User toEntity(RegisterRequest dto) {
        if (dto == null) {
            return null;
        }
        return User.create(
                dto.credentials().email(),
                dto.firstName(),
                dto.lastName()
        );
    }

    @Override
    public AuthResponse toDto(User user) {
        if (user == null) {
            return null;
        }

        // ? why null ?
        // => to add it somewhere else
        // => User -> JwtService.generateToker()
        // => User -x-> mapper
        return new AuthResponse(null);
    }
}
