package com.two.ufcard.dao.repository;

import com.two.ufcard.dao.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository <E extends Entity> extends JpaRepository<E, String> {

}
