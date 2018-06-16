package com.two.ufcard.protocol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto implements Dto, Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String login;
    private String cardNumber;
}
