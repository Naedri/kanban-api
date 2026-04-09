package com.naedri.kanban_api.dto.auth;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

/**
 * An object the Rest API will accept to login an user (calling the auth service layer)
 */
public record LoginRequest(
        @NotNull
        Credentials credentials,
        @Nullable
        Boolean rememberMe
) {
}
