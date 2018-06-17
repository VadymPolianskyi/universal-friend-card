package com.two.ufcard.facade;

import com.two.ufcard.dao.entity.security.UserCredentials;
import com.two.ufcard.dao.repository.UserDetailsRepository;
import com.two.ufcard.protocol.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserCredentialsService implements UserDetailsService {

    private final UserDetailsRepository repository;

    @Autowired
    public UserCredentialsService(UserDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws IllegalArgumentException {
        return findById(username).orElseThrow(() -> new UsernameNotFoundException("There is no username with " + username));
    }

    public Optional<UserCredentials> findById(String id) {
        return repository.findById(id);
    }

    public UserCredentials create(UserDto user, String password) {
        return create(new UserCredentials(user.getId(), user.getLogin(), true, true,
                true, password, null));
    }

    private UserCredentials create(UserCredentials entity) {
        return repository.save(entity);
    }


}
