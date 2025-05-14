package ru.ifellow.jschool.machmetshin.entity.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, MANAGER, ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
