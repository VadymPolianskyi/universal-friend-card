package com.two.ufcard.protocol;

import com.two.ufcard.protocol.api.Request;
import com.two.ufcard.protocol.dto.UserDto;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequest extends Request {
    @Valid
    @NonNull
    private UserDto user;

    @NotEmpty
    private String password;
}
