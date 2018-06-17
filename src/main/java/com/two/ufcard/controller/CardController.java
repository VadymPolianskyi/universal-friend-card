package com.two.ufcard.controller;

import com.two.ufcard.dao.CardDao;
import com.two.ufcard.dao.UserDao;
import com.two.ufcard.dao.entity.Card;
import com.two.ufcard.dao.entity.security.UserCredentials;
import com.two.ufcard.protocol.AddCardRequest;
import com.two.ufcard.protocol.AddCardResponse;
import com.two.ufcard.protocol.DeleteCardResponse;
import com.two.ufcard.protocol.dto.CardDto;
import com.two.ufcard.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
public class CardController {

    private final CardDao cardDao;
    private final UserDao userDao;
    private final Mapper mapper;

    @Autowired
    public CardController(CardDao cardDao, Mapper mapper, UserDao userDao) {
        this.cardDao = cardDao;
        this.mapper = mapper;
        this.userDao = userDao;
    }

    @PostMapping
    public AddCardResponse addCard( @AuthenticationPrincipal UserCredentials credentials,
            @RequestBody AddCardRequest request) {
        CardDto dto = request.getCard();

        Card card = mapper.map(dto, Card.class);
        card.setUser(userDao.findById(credentials.getId()).get());
        card = cardDao.create(card);

        dto.setId(card.getId());
        dto.setUserId(credentials.getId());
        return new AddCardResponse(dto);
    }

    @DeleteMapping("/{cardId}")
    public DeleteCardResponse deleteCard(@PathVariable String cardId) {
        cardDao.delete(cardId);
        return new DeleteCardResponse(true);
    }

//    @GetMapping("/all")
}
