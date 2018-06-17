package com.two.ufcard.controller;

import com.two.ufcard.dao.UserDao;
import com.two.ufcard.dao.UserDetailsDao;
import com.two.ufcard.dao.entity.User;
import com.two.ufcard.dao.entity.security.UserCredentials;
import com.two.ufcard.protocol.LoginUserRequest;
import com.two.ufcard.protocol.LoginUserResponse;
import com.two.ufcard.util.auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@Slf4j
public class LoginController {

    private final AuthService authService;
    private final UserDao userDao;
    private final UserDetailsDao userDetailsDao;

    @Autowired
    public LoginController(AuthService authService, UserDao userDao, UserDetailsDao userDetailsDao) {
        this.authService = authService;
        this.userDao = userDao;
        this.userDetailsDao = userDetailsDao;
    }

    @PostMapping
    public LoginUserResponse loginUser(@RequestBody LoginUserRequest request) {

        User user = userDao.findByLogin(request.getLogin());

        if (!user.getPassword().equals(request.getPassword())) {
            log.error("Password '{}' is wrong for user with login '{}'..", request.getPassword(), request.getLogin());
//            throw WrongPasswordException("Password " + request.getPassword() + " is wrong for user with login " + request.getLogin() + "..")
        }

        OAuth2AccessToken token = authService.login(user.getId());

        log.info("Authenticated user {} {}({}).", user.getFirstName(), user.getLastName(), user.getCardNumber());
        return new LoginUserResponse(token.getValue(), token.getTokenType(), token.getExpiresIn(), token.getRefreshToken().getValue());
    }
}
