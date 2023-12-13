package com.ildan.testing.opencode.config;

import com.ildan.testing.opencode.model.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class SecurityUser implements UserDetails {

    private String userName;
    private String userPassword;
    private boolean isActive;
    private List<GrantedAuthority> grantedAuthorities;

    public SecurityUser(String userName, String userPassword, boolean isActive, List<GrantedAuthority> grantedAuthorities) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.isActive = isActive;
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),
                user.isActive(),
                user.isActive(),
                user.isActive(),
                user.isActive(),
                user.getRole().getAuthorities()
        );
    }
}