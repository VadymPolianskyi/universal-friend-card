package com.two.ufcard.facade.api;

import com.two.ufcard.protocol.dto.CardDto;

import java.util.List;

public interface ICardFacade extends Facade<CardDto> {
    List<CardDto> findByUser(String userId);
}
