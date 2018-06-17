package com.two.ufcard.dao;

import com.two.ufcard.dao.entity.security.UserCredentials;
import com.two.ufcard.dao.repository.UserDetailsRepository;
import com.two.ufcard.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsDao implements UserDetailsService {

    protected final UserDetailsRepository repository;

    @Autowired
    public UserDetailsDao(UserDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws IllegalArgumentException {
        return findById(username).orElseThrow(() -> new UsernameNotFoundException("There is no username with " + username));
    }

    public Optional<UserCredentials> findById(String id) {
        return repository.findById(id);
    }

    public UserCredentials create(UserCredentials entity) {
        return repository.save(entity);
    }
}
