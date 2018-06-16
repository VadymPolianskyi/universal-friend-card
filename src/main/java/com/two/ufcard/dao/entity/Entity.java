package com.two.ufcard.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entity {
    @Id
    @GenericGenerator(name = "uuid_generator", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_generator")
    private String id;
}
