package com.naedri.kanban_api.dto.auth;

/**
 * A DTO representing a connected user with jwt.
 */
public record AuthResponse(
        // ? can org.springframework.security.core.token.Token be used instead of String ?
        // => No, this class is for internal remember-me tokens of Spring, not JWT
        String token
) {
}
