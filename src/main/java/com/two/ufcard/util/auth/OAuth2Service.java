package com.two.ufcard.util.auth;

import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface OAuth2Service {
    OAuth2AccessToken getAccess(String userId);

    void revokeToken(String userId);
}
