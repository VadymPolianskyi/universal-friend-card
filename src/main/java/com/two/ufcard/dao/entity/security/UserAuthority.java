package com.two.ufcard.dao.entity.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
public class UserAuthority implements GrantedAuthority {

    private String role;

    @Override
    public String getAuthority() {
        return null;
    }
}
