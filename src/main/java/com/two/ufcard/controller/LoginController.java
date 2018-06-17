package com.two.ufcard.controller;

import com.two.ufcard.dao.UserDao;
import com.two.ufcard.dao.entity.User;
import com.two.ufcard.dao.entity.security.UserCredentials;
import com.two.ufcard.facade.UserCredentialsService;
import com.two.ufcard.facade.UserFacade;
import com.two.ufcard.protocol.LoginUserRequest;
import com.two.ufcard.protocol.LoginUserResponse;
import com.two.ufcard.protocol.dto.UserDto;
import com.two.ufcard.protocol.exception.LoginException;
import com.two.ufcard.protocol.exception.UserNotFoundException;
import com.two.ufcard.protocol.exception.WrongPasswordException;
import com.two.ufcard.util.auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
@Slf4j
public class LoginController {

    private final AuthService authService;
    private final UserFacade userFacade;
    private final UserCredentialsService credentialsService;

    @Autowired
    public LoginController(AuthService authService, UserFacade userFacade, UserCredentialsService credentialsService) {
        this.authService = authService;
        this.userFacade = userFacade;
        this.credentialsService = credentialsService;
    }

    @PostMapping
    public LoginUserResponse loginUser(@RequestBody LoginUserRequest request) {

        UserDto user = Optional.ofNullable(userFacade.findByLogin(request.getLogin())).orElseThrow(() ->
                new UserNotFoundException("User with login " + request.getLogin() + " not found."));

        UserCredentials credentials = credentialsService.findById(user.getId()).orElseThrow(LoginException::new);

        if (!credentials.getPassword().equals(request.getPassword())) {
            log.error("Password '{}' is wrong for user with login '{}'..", request.getPassword(), request.getLogin());
            throw new WrongPasswordException("Password " + request.getPassword() + " is wrong for user with login " + request.getLogin() + "..");
        }

        OAuth2AccessToken token = authService.login(user.getId());

        log.info("Authenticated user {} {}({}).", user.getFirstName(), user.getLastName(), user.getCardNumber());
        return new LoginUserResponse(token.getValue(), token.getTokenType(), token.getExpiresIn(), token.getRefreshToken().getValue());
    }
}
