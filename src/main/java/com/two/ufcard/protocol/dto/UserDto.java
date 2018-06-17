package com.two.ufcard.protocol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto implements Dto {
    private String id;
    private String firstName;
    private String lastName;
    private String login;
    private String cardNumber;
}
