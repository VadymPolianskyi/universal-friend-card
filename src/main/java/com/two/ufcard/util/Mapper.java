package com.two.ufcard.util;

import com.two.ufcard.dao.entity.Card;
import com.two.ufcard.dao.entity.Entity;
import com.two.ufcard.dao.entity.User;
import com.two.ufcard.protocol.dto.CardDto;
import com.two.ufcard.protocol.dto.Dto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Mapper {
    private ModelMapper mapper = new ModelMapper();

    public <E extends Entity, D extends Dto> E map(D dto, Class<E> entityType) {
        log.debug("Mapped from {} to {} class type.", dto.getClass().getName(), entityType.getName());
        return mapper.map(dto, entityType);
    }

    public <D extends Dto, E> D revert(E entity, Class<D> dtoType) {
        log.debug("Mapped from {} to {} class type.", entity.getClass().getName(), dtoType.getName());
        return mapper.map(entity, dtoType);
    }

    public Card mapCard(CardDto dto, User user) {
        Card card = mapper.map(dto, Card.class);
        card.setUser(user);
        log.debug("Mapped from CardDto to Card class type.");
        return card;
    }

    public CardDto revertCard(Card card) {
        CardDto dto = mapper.map(card, CardDto.class);
        if (card.getUser() != null)
            dto.setUserId(card.getUser().getId());

        log.debug("Mapped from Card to CardDto class type.");
        return dto;
    }
}
