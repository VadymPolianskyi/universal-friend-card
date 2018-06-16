package com.two.ufcard.dao.entity;

import lombok.AllArgsConstructor;

@javax.persistence.Entity
@AllArgsConstructor
public class User extends Entity {
    private String firstName;
    private String lastName;
    private Integer age;
    private String login;
    private String password;
}
