package com.two.ufcard.config;

import com.two.ufcard.dao.UserDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private AuthenticationManager authenticationManager;
    private UserDetailsDao userDetailsDao;
    private DataSource dataSource;

    @Autowired
    public AuthorizationServerConfig(AuthenticationManager authenticationManager, UserDetailsDao userDetailsDao, DataSource dataSource) {
        this.authenticationManager = authenticationManager;
        this.userDetailsDao = userDetailsDao;
        this.dataSource = dataSource;
    }

    @Value("${token.validity.seconds}")
    private Integer tokenValiditySeconds;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("my-trusted-client")
                .authorizedGrantTypes("client_credentials", "password", "refresh_token")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .resourceIds("oauth2-resource")
                .accessTokenValiditySeconds(tokenValiditySeconds)
                .secret("secret");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
        //JWT
        .userDetailsService(userDetailsDao)
        .tokenStore(tokenStore())
        .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public AccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        converter.setSigningKey("signing-key");
        ((DefaultAccessTokenConverter)converter.getAccessTokenConverter()).setUserTokenConverter(userAuthenticationConverter());
        return converter;
    }

    @Bean
    public UserAuthenticationConverter userAuthenticationConverter() {
        UserAuthenticationConverter converter = new DefaultUserAuthenticationConverter();
        ((DefaultUserAuthenticationConverter) converter).setUserDetailsService(userDetailsDao);
        return converter;
    }
}
