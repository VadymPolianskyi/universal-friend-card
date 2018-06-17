package com.two.ufcard.facade;

import com.two.ufcard.dao.CardDao;
import com.two.ufcard.dao.UserDao;
import com.two.ufcard.dao.entity.Card;
import com.two.ufcard.dao.entity.User;
import com.two.ufcard.facade.api.FacadeWithDao;
import com.two.ufcard.facade.api.ICardFacade;
import com.two.ufcard.protocol.dto.CardDto;
import com.two.ufcard.protocol.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CardFacade extends FacadeWithDao<Card, CardDto, CardDao> implements ICardFacade {

    private final UserDao userDao;

    @Autowired
    public CardFacade(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public CardDto create(CardDto dto) {
        User user = userDao.findById(dto.getUserId()).orElseThrow(() ->
                new UserNotFoundException("User with id " + dto.getUserId() + " not found."));

        Card card = getMapper().mapCard(dto, user);
        card = getDao().create(card);

        log.debug("Saved card with owner {} and number{}-****-**** of user {}.", card.getOwner(),
                card.getNumber().substring(0, 9), card.getUser().getLogin());
        return getMapper().revertCard(card);
    }

    @Override
    public CardDto read(String id) {
        log.debug("Read card with id {}", id);
        return getDao().findById(id)
                .map(card -> getMapper().revertCard(card))
                .orElseThrow(() -> new UserNotFoundException("Card with id " + id + " not found."));
    }

    @Override
    public CardDto update(CardDto dto) {
        User user = userDao.findById(dto.getUserId()).orElseThrow(() ->
                new UserNotFoundException("User with id " + dto.getUserId() + " not found."));

        Card card = getMapper().mapCard(dto, user);
        card = getDao().create(card);

        log.debug("Updated card with owner {} and number{}-****-**** of user {}.", card.getOwner(),
                card.getNumber().substring(0, 9), card.getUser().getLogin());
        return getMapper().revertCard(card);
    }

    @Override
    public void delete(String id) {
        log.debug("Saved card with id {}.", id);
        getDao().delete(id);
    }

    @Override
    public List<CardDto> findByUser(String userId) {
        User user = userDao.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User with id " + userId + " not found."));

        log.debug("Get list of cards of user {}.", user.getLogin());
        return getDao().findAllByUser(user).stream()
                .map(this::revert)
                .collect(Collectors.toList());
    }

    @Override
    protected Class<Card> entityClass() {
        return Card.class;
    }

    @Override
    protected Class<CardDto> dtoClass() {
        return CardDto.class;
    }
}
