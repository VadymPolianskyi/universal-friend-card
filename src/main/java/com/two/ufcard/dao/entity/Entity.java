package com.two.ufcard.dao.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class Entity {
    @Id
    @GenericGenerator(name = "uuid_generator", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_generator")
    private String id;
}
