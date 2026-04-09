package com.naedri.kanban_api.controller;

import com.naedri.kanban_api.domain.model.User;
import com.naedri.kanban_api.dto.auth.AuthResponse;
import com.naedri.kanban_api.dto.auth.LoginRequest;
import com.naedri.kanban_api.dto.auth.RegisterRequest;
import com.naedri.kanban_api.security.JwtService;
import com.naedri.kanban_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    /**
     * `register -> password hashed -> user saved -> JWT returned`
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request
    ) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.credentials().email(),
                request.credentials().password()
        );
        // ? how does it affect the userService to allow using getCurrentUser ?
        // AuthenticationManager triggers the chain provider of Spring Security.
        // ? why manipulating the whole UserDetails instead of User
        // ? why getPrincipal result is enough ?
        UserDetails user = (UserDetails) authenticationManager
                .authenticate(authentication)
                .getPrincipal();

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    /**
     * `login -> credentials verified -> JWT returned`
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
    ) {
        // ? why using User and not UserDetails
        User user = userService.register(request);
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(token));

    }
}
