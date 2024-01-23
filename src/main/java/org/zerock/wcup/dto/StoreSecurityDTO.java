package org.zerock.wcup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreSecurityDTO implements UserDetails {

    private Long sno;

    private String title;

    private String pw;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(sno == 1L ){

            return java.util.List.of( new SimpleGrantedAuthority("ROLE_STORE"), new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return java.util.List.of(new SimpleGrantedAuthority("ROLE_STORE"));
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public String getUsername() {
        return String.valueOf(sno);
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
