package com.two.ufcard.util.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthServiceImpl implements AuthService {

    private final OAuth2Service oauth;

    @Autowired
    public AuthServiceImpl(OAuth2Service oauth) {
        this.oauth = oauth;
    }

    @Override
    public OAuth2AccessToken login(String userId) {
        return oauth.getAccess(userId);
    }

    @Override
    public void logout(HttpServletRequest request) throws UsernameNotFoundException {

    }
}
