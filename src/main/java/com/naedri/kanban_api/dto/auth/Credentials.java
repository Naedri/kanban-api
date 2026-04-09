package com.naedri.kanban_api.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * An object the Rest API will accept to login an user (calling the auth service layer)
 * This class is owned by the presentation layer.
 * Its constraints allow to validate it.
 * <p>
 * To reduce code duplication between LoginRequest and RegisterRequest
 */
public record Credentials(
        @NotBlank(message = ERROR_MESSAGE_EMAIL_LENGTH)
        @Length(max = 255)
        @Email
        String email,

        // ? can a more specific spring.Security class be used
        // ? how to ensure it is not raw pdw but encrypted one ?
        // => we should not received an hashed pwd, it has to be done by PasswordEncoder.encode.
        @NotBlank
        @Length(max = 255)
        String password
) {
    private static final String ERROR_MESSAGE_EMAIL_LENGTH = "Email must be less than 254 characters.";
}