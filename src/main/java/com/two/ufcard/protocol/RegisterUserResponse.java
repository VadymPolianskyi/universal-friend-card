package com.two.ufcard.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserResponse {
    private String token;
    private String type;
    private Integer expiresIn;
    private String refresh;
}
