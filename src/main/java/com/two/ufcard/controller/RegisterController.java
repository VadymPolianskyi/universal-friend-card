package com.two.ufcard.controller;

import com.two.ufcard.dao.UserDao;
import com.two.ufcard.dao.UserDetailsDao;
import com.two.ufcard.dao.entity.User;
import com.two.ufcard.dao.entity.security.UserCredentials;
import com.two.ufcard.protocol.RegisterUserRequest;
import com.two.ufcard.protocol.RegisterUserResponse;
import com.two.ufcard.util.Mapper;
import com.two.ufcard.util.auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
@Slf4j
public class RegisterController {

    private final AuthService authService;
    private final UserDao userDao;
    private final Mapper mapper;
    private final UserDetailsDao userDetailsDao;

    @Autowired
    public RegisterController(AuthService authService, UserDao userDao, Mapper mapper, UserDetailsDao userDetailsDao) {
        this.authService = authService;
        this.userDao = userDao;
        this.mapper = mapper;
        this.userDetailsDao = userDetailsDao;
    }

    @PostMapping
    public RegisterUserResponse register(@RequestBody @Valid RegisterUserRequest request) {

        User user = mapper.map(request.getUser(), User.class);
        user.setPassword(request.getPassword());

        userDao.create(user);

        UserCredentials userCredentials = userDetailsDao.create(new UserCredentials(user.getId(), true, true,
                true, request.getPassword(), null));

        OAuth2AccessToken token = authService.login(userCredentials.getId());

        log.info("Registered new user {} {}({})." + user.getFirstName(), user.getLastName(), user.getCardNumber());

        return new RegisterUserResponse(token.getValue(), token.getTokenType(), token.getExpiresIn(), token.getRefreshToken().getValue());
    }
}
