package com.two.ufcard.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@javax.persistence.Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Entity {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String cardNumber;
}
