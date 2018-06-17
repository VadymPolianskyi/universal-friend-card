package com.two.ufcard.facade.api;

import com.two.ufcard.protocol.dto.UserDto;

public interface IUserFacade extends Facade<UserDto> {
    UserDto findByLogin(String login);
}
