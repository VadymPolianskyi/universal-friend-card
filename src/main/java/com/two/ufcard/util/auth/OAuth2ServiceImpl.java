package com.two.ufcard.util.auth;

import com.two.ufcard.dao.UserDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public class OAuth2ServiceImpl implements OAuth2Service {

    private final TokenStore tokenStore;
    private final AuthorizationServerEndpointsConfiguration configuration;
    private final UserDetailsDao userDetailsDao;

    @Autowired
    public OAuth2ServiceImpl(TokenStore tokenStore, AuthorizationServerEndpointsConfiguration configuration, UserDetailsDao userDetailsDao) {
        this.tokenStore = tokenStore;
        this.configuration = configuration;
        this.userDetailsDao = userDetailsDao;
    }

    @Override
    public OAuth2AccessToken getAccess(String userId) {
        Map<String, String> requestParams = new HashMap<>();
        Map<String, Serializable> extensionProps = new HashMap<>();

        Set<String> responseTypes = new HashSet<>();
        responseTypes.add("code");

        List<String> scopes = Arrays.asList("read", "write");

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        OAuth2Request oAuth2Request = new OAuth2Request(requestParams, "my-trusted-client",
                authorities, true, new HashSet<>(scopes), new HashSet<>(Collections.singletonList("oauth2-resource")),
                null, responseTypes, extensionProps);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetailsDao.loadUserByUsername(userId), "N/A", authorities);

        OAuth2Authentication auth = new OAuth2Authentication(oAuth2Request, authenticationToken);

        AuthorizationServerTokenServices tokenServices = configuration.getEndpointsConfigurer().getTokenServices();
        return tokenServices.createAccessToken(auth);
    }

    @Override
    public void revokeToken(String userId) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(userId);
        tokenStore.removeAccessToken(accessToken);
    }
}
