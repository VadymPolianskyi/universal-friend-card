package com.two.ufcard.dao;

import com.two.ufcard.dao.entity.Card;
import com.two.ufcard.dao.entity.User;
import com.two.ufcard.dao.repository.CardRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardDao extends GenericDao<Card, CardRepository> {
    public List<Card> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }
}
