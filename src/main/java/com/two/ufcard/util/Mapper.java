package com.two.ufcard.util;

import com.two.ufcard.dao.entity.Entity;
import com.two.ufcard.protocol.dto.Dto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Mapper {
    private ModelMapper mapper = new ModelMapper();

    public <E extends Entity, D extends Dto> E map(D dto, Class<E> entityType) {

        return mapper.map(dto, entityType);
    }

    public <D extends Entity, E> D revert(E entity, Class<D> dtoType) {
        return mapper.map(entity, dtoType);
    }
}
