package com.two.ufcard.protocol;

import com.two.ufcard.protocol.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequest {
    private UserDto user;
    private String password;
}
