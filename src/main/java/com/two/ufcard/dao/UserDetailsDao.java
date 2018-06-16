package com.two.ufcard.dao;

import com.two.ufcard.dao.entity.security.UserCredentials;
import com.two.ufcard.dao.repository.UserDetailsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsDao extends GenericDao<UserCredentials, UserDetailsRepository> implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws IllegalArgumentException {
        return findById(username).orElseThrow(() -> new UsernameNotFoundException("There is no username with " + username));
    }
}
