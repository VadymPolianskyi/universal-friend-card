package com.two.ufcard.dao;

import com.two.ufcard.dao.entity.Card;
import com.two.ufcard.dao.repository.CardRepository;
import org.springframework.stereotype.Component;

@Component
public class CardDao extends GenericDao<Card, CardRepository> {
}
