package com.naedri.kanban_api.dto.auth;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

/**
 * An object the Rest API will accept to authenticate an user (calling the auth service layer)
 */
public record RegisterRequest(
        @NotNull
        Credentials credentials,
        @Nullable
        String firstName,
        @Nullable
        String lastName
) {
}
