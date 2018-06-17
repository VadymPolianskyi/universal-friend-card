package com.two.ufcard.facade.api;

import com.two.ufcard.dao.GenericDao;
import com.two.ufcard.dao.entity.Entity;
import com.two.ufcard.dao.repository.Repository;
import com.two.ufcard.protocol.dto.Dto;
import com.two.ufcard.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class FacadeWithDao<E extends Entity,
        D extends Dto,
        Dao extends GenericDao<E, ? extends Repository<E>>> {
    @Autowired
    private Dao dao;
    @Autowired
    private Mapper mapper;

    protected abstract Class<E> entityClass();
    protected abstract Class<D> dtoClass();

    protected E map(D dto) {
        return mapper.map(dto, entityClass());
    }

    protected D revert(E entity) {
        return mapper.revert(entity, dtoClass());
    }

    public Mapper getMapper() {
        return mapper;
    }

    public Dao getDao() {
        return dao;
    }
}
