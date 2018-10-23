package com.smerp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.smerp.jwt.models.Users;

@Repository
public interface UserDao extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
}
