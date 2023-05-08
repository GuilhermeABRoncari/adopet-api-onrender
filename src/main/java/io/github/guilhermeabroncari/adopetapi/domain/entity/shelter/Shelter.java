package io.github.guilhermeabroncari.adopetapi.domain.entity.shelter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.guilhermeabroncari.adopetapi.domain.entity.adress.Adress;
import io.github.guilhermeabroncari.adopetapi.domain.entity.pet.Pet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "Shelter")
@Table(name = "shelters")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Shelter implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "shelter_name")
    private String shelterName;
    private String email;
    private String phone;
    private String password;
    private String about;
    @Column(name = "shelter_profile_image")
    private String shelterProfileImage;
    @Embedded
    private Adress adress;
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<Pet> petList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_SHELTER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public String getPassword() {
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

    public void update(ShelterUpdateDTO dto) {
        if (dto.shelterName() != null) this.shelterName = dto.shelterName();
        if (dto.phone() != null) this.password = dto.phone();
        if (dto.about() != null) this.about = dto.about();
        if (dto.profileImage() != null) this.shelterProfileImage = dto.profileImage();
        if (dto.adress() != null) this.adress.update(dto.adress());
    }
}
