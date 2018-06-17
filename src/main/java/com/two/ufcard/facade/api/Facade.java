package com.two.ufcard.facade.api;

import com.two.ufcard.protocol.dto.Dto;

interface Facade< D extends Dto> {

    D create(D dto);
    D read(String id);
    D update(D dto);
    void delete(String id);
}
