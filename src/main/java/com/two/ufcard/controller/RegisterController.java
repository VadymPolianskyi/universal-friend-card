package com.two.ufcard.controller;

import com.two.ufcard.facade.UserCredentialsService;
import com.two.ufcard.dao.entity.security.UserCredentials;
import com.two.ufcard.facade.UserFacade;
import com.two.ufcard.protocol.RegisterUserRequest;
import com.two.ufcard.protocol.RegisterUserResponse;
import com.two.ufcard.protocol.dto.UserDto;
import com.two.ufcard.util.auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
@Slf4j
public class RegisterController {

    private final AuthService authService;
    private final UserCredentialsService credentialsService;
    private final UserFacade userFacade;

    @Autowired
    public RegisterController(AuthService authService, UserCredentialsService credentialsService, UserFacade userFacade) {
        this.authService = authService;
        this.credentialsService = credentialsService;
        this.userFacade = userFacade;
    }

    @PostMapping
    public RegisterUserResponse register(@RequestBody RegisterUserRequest request) {


        UserDto user = userFacade.create(request.getUser());
        UserCredentials userCredentials = credentialsService.create(user, request.getPassword());

        OAuth2AccessToken token = authService.login(userCredentials.getId());

        log.info("Registered new user {} {}({}).", user.getFirstName(), user.getLastName(), user.getCardNumber());

        return new RegisterUserResponse(token.getValue(), token.getTokenType(), token.getExpiresIn(), token.getRefreshToken().getValue());
    }
}
