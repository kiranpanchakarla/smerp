package com.smerp.service;

import java.util.List;

import com.smerp.jwt.models.UserDto;
import com.smerp.jwt.models.Users;

public interface UserService {

	Users save(UserDto user);

	List<Users> findAll();

	void delete(long id);

	Users findOne(String username);

	Users findById(Long id);
}
