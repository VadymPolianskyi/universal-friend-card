package com.two.ufcard.protocol;

import com.two.ufcard.protocol.api.Request;
import com.two.ufcard.protocol.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequest extends Request {
    private UserDto user;
    private String password;
}
