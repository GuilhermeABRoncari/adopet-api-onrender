package io.github.guilhermeabroncari.adopetapi.domain.entity.tutor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.guilhermeabroncari.adopetapi.domain.entity.adress.Adress;
import io.github.guilhermeabroncari.adopetapi.domain.entity.user.UserRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.beans.Encoder;
import java.util.Collection;
import java.util.List;

@Entity(name = "Tutor")
@Table(name = "tutors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Tutor implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tutor_name")
    private String tutorName;
    private String email;
    private String phone;
    private String password;
    private String about;
    @Column(name = "tutor_profile_image")
    private String tutorProfileImage;
    @Embedded
    private Adress adress;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_TUTOR"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public String getPassord() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void update(TutorUpdateDTO dto) {
        if (dto.tutorName() != null) this.tutorName = dto.tutorName();
        if (dto.phone() != null) this.phone = dto.phone();
        if (dto.about() != null) this.about = dto.about();
        if (dto.profileImage() != null) this.tutorProfileImage = dto.profileImage();
        if (dto.adress() != null) this.adress.update(dto.adress());
    }
}
