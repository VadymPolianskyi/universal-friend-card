package com.two.ufcard.dao.entity.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAuthority implements GrantedAuthority {

    @Id
    private String role;

    @Override
    public String getAuthority() {
        return null;
    }
}
