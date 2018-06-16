package com.two.ufcard.dao;

import com.two.ufcard.dao.entity.Entity;
import com.two.ufcard.dao.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class GenericDao<E extends Entity, R extends Repository<E>> {

    @Autowired
    protected R repository;

    public Optional<E> findById(String id) {
        return repository.findById(id);
    }

    public List<E> findAll() {
        return repository.findAll();
    }

    public E create(E entity) {
        return repository.save(entity);
    }

    public E update(E entity) {
        return repository.save(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
