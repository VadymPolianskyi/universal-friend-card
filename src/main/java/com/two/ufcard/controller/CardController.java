package com.two.ufcard.controller;

import com.two.ufcard.dao.entity.security.UserCredentials;
import com.two.ufcard.facade.CardFacade;
import com.two.ufcard.facade.UserFacade;
import com.two.ufcard.protocol.AddCardRequest;
import com.two.ufcard.protocol.AddCardResponse;
import com.two.ufcard.protocol.AllCardsResponse;
import com.two.ufcard.protocol.DeleteCardResponse;
import com.two.ufcard.protocol.dto.CardDto;
import com.two.ufcard.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
@Slf4j
public class CardController {

    private final CardFacade cardFacade;

    @Autowired
    public CardController(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    @PostMapping
    public AddCardResponse addCard(@AuthenticationPrincipal UserCredentials credentials,
            @RequestBody AddCardRequest request) {
        request.getCard()
                .setUserId(credentials.getId());

        CardDto card = cardFacade.create(request.getCard());

        log.info("User {} added new card with owner {} and card number {}-****-****",
                credentials.getName(), card.getOwner(), card.getNumber().substring(0, 9));

        return new AddCardResponse(card);
    }

    @DeleteMapping("/{cardId}")
    public DeleteCardResponse deleteCard(@AuthenticationPrincipal UserCredentials credentials,
                                         @PathVariable String cardId) {
        cardFacade.delete(cardId);
        log.info("User {} deleted card with id {}",
                credentials.getName(), cardId);
        return new DeleteCardResponse(true);
    }

    @GetMapping("/all")
    public AllCardsResponse allCards(@AuthenticationPrincipal UserCredentials credentials) {

        List<CardDto> cards = cardFacade.findByUser(credentials.getId());

        log.info("User {} get all his cards.",
                credentials.getName());
        return new AllCardsResponse(cards);
    }
}
