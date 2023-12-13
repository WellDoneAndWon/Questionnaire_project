package com.ildan.testing.opencode.model.enums;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    USER(Set.of(Permission.USER_FORM)),
    ADMIN(Set.of(Permission.ADMIN_FORM));

    @Getter
    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getTitle()))
                .collect(Collectors.toSet());
    }
}