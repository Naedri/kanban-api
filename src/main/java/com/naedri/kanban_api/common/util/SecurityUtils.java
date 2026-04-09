package com.naedri.kanban_api.common.util;

import com.naedri.kanban_api.domain.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

// ? why @NoArgsConstructor(access = AccessLevel.PRIVATE)
// => To prevents instantiation : 
// - `new SecurityUtils()` not allowed
@NoArgsConstructor(access = AccessLevel.PRIVATE)

// ? why is it final
// => Because it is a utility class
// - it is not meant to be extended
// - it contains only static helper methods
public final class SecurityUtils {

    private static final String ERROR_MESSAGE_USER_AUTHENTICATION = "User not authenticated";
    
    public static User getCurrentUser() {
        // SecurityContextHolder includes Authentication injected by JwtAuthenticationFilter.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException(ERROR_MESSAGE_USER_AUTHENTICATION);
        }

        Object user = authentication.getPrincipal();
        if (!(user instanceof User)) {
            throw new IllegalStateException(ERROR_MESSAGE_USER_AUTHENTICATION);
        }

        return (User) user;
    }
}
