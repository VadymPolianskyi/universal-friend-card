package com.two.ufcard.protocol;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class LoginUserRequest {
    private String login;
    private String password;
}
