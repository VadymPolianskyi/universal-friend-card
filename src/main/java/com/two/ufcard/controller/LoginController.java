package com.two.ufcard.controller;

import com.two.ufcard.protocol.LoginUserRequest;
import com.two.ufcard.protocol.LoginUserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @PostMapping("/")
    public LoginUserResponse loginUser(LoginUserRequest request) {
        return null;
    }
}
