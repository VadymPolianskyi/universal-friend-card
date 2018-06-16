package com.two.ufcard.util.auth;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    OAuth2AccessToken login(String userId);

    void logout(HttpServletRequest request) throws UsernameNotFoundException;
}
