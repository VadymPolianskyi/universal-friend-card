package com.two.ufcard.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card extends com.two.ufcard.dao.entity.Entity {
    private String owner; //owner from real life
    private String number;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; //user from app
}
