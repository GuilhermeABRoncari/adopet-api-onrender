package io.github.guilhermeabroncari.adopetapi.infra.security;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();
}
