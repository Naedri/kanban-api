package com.naedri.kanban_api.service;

import com.naedri.kanban_api.domain.model.User;
import com.naedri.kanban_api.dto.auth.RegisterRequest;

public interface UserService {
    User register(RegisterRequest request);

    User getCurrentUser();
}
