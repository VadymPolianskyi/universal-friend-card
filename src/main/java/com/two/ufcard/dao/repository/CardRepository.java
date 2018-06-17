package com.two.ufcard.dao.repository;

import com.two.ufcard.dao.entity.Card;
import com.two.ufcard.dao.entity.User;

import java.util.List;

public interface CardRepository extends Repository<Card> {
    List<Card> findAllByUser(User user);
}
