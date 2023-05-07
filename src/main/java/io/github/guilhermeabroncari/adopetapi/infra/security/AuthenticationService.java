package io.github.guilhermeabroncari.adopetapi.infra.security;

import io.github.guilhermeabroncari.adopetapi.domain.entity.shelter.ShelterRepository;
import io.github.guilhermeabroncari.adopetapi.domain.entity.tutor.TutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private ShelterRepository shelterRepository;
    private TutorRepository tutorRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var shelter = shelterRepository.findByEmail(username);
        var tutor = tutorRepository.findByEmail(username);
        if(shelter != null){
            return shelter;
        } else if (tutor != null) {
            return tutor;
        }throw new UsernameNotFoundException("User not found");
    }
}
