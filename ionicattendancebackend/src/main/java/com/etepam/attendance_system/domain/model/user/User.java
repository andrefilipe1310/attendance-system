package com.etepam.attendance_system.domain.model.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "tb_user")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private UserRoles roles;

    public User(UserRequestDTO userRequestDTO){
        this.update(userRequestDTO);
    }
    public User(String email, String password, UserRoles roles){
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public void update(UserRequestDTO userRequestDTO){
        this.email = userRequestDTO.email();
        this.password = userRequestDTO.password();
    }
    public UserResponseDTO toDTO(){
        return new UserResponseDTO(
                this.id,
                this.email
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roles == UserRoles.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else {
            return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
        }
    }

    @Override
    public String getUsername() {
        return this.email;
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
}
