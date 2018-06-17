package com.two.ufcard.protocol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto implements Dto {
    private String id;
    private String firstName;
    private String lastName;
    @NotEmpty
    private String login;
    @NotEmpty
    private String cardNumber;
}
