package com.naedri.kanban_api.mapper;

import com.naedri.kanban_api.domain.model.User;
import com.naedri.kanban_api.dto.auth.AuthResponse;
import com.naedri.kanban_api.dto.auth.RegisterRequest;

public interface RegisterMapper {
    User toEntity(RegisterRequest sto);

    AuthResponse toDto(User user);
}