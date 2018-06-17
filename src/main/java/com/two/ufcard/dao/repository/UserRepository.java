package com.two.ufcard.dao.repository;

import com.two.ufcard.dao.entity.User;

public interface UserRepository extends Repository<User> {
    User findByLogin(String login);
}
