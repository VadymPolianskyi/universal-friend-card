package com.two.ufcard.dao.repository;

import com.two.ufcard.dao.entity.security.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserCredentials, String> {
}
