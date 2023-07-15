package io.github.guilhermeabroncari.adopetapi.infra.security;

import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.TutorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private TutorRepository tutorRepository;
    private ShelterRepository shelterRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = tokenRecovery(request);
        if (token != null) {
            var subject = tokenService.getSubject(token);
            var tutor = tutorRepository.findByEmail(subject);
            var shelter = shelterRepository.findByEmail(subject);
            UsernamePasswordAuthenticationToken authentication = null;

            if (tutor != null)
                authentication = new UsernamePasswordAuthenticationToken(tutor, null, tutor.getAuthorities());
            if (shelter != null)
                authentication = new UsernamePasswordAuthenticationToken(shelter, null, shelter.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String tokenRecovery(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}