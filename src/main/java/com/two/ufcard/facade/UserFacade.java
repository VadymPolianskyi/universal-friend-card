package com.two.ufcard.facade;

import com.two.ufcard.dao.UserDao;
import com.two.ufcard.dao.entity.User;
import com.two.ufcard.facade.api.FacadeWithDao;
import com.two.ufcard.facade.api.IUserFacade;
import com.two.ufcard.protocol.dto.UserDto;
import com.two.ufcard.protocol.exception.RegistrationException;
import com.two.ufcard.protocol.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
@Slf4j
public class UserFacade extends FacadeWithDao<User, UserDto, UserDao> implements IUserFacade {

    @Override
    public UserDto create(UserDto dto) {
        if (findByLogin(dto.getLogin()) != null) {
            log.error("User with login {} already exists.", dto.getLogin());
            throw new RegistrationException("User with login " + dto.getLogin() + " already exists.");
        }
        User user = getDao().create(map(dto));
        log.debug("Saved user with login {}", user.getLogin());
        return revert(user);
    }

    @Override
    public UserDto read(String id) {
        log.debug("Read user with id {}", id);
        return getDao().findById(id)
                .map(this::revert)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found."));
    }

    @Override
    public UserDto update(UserDto dto) {
        User user = getDao().update(map(dto));
        log.debug("Update user with login {}", user.getLogin());
        return revert(user);
    }

    @Override
    public void delete(String id) {
        getDao().delete(id);
        log.debug("Success delete user with id {}", id);
    }

    @Override
    public UserDto findByLogin(String login) {
        log.debug("Read user with login {}", login);
        return Optional.ofNullable(getDao().findByLogin(login))
                .map(this::revert).orElse(null);
    }

    @Override
    protected Class<User> entityClass() {
        return User.class;
    }

    @Override
    protected Class<UserDto> dtoClass() {
        return UserDto.class;
    }
}
