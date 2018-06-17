package com.two.ufcard.dao;

import com.two.ufcard.dao.entity.User;
import com.two.ufcard.dao.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserDao extends GenericDao<User, UserRepository> {
    public User findByLogin(String login) {
        return repository.findByLogin(login);
    }
}
