package com.naedri.kanban_api.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "spring.security.jwt")
public class JwtProperties {

    private final String secret;
    private final Integer expiration;

    public JwtProperties(
            String secret,
            Integer expiration
    ) {

        this.secret = secret != null
                ? secret
                : "td5rJGOmNv0rAs4YpbjSvQUVb79nAy5ZBhid0yLm244=";

        this.expiration = expiration != null
                ? expiration
                : 86400000;
    }
}