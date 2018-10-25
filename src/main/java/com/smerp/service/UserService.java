package com.smerp.service;

import java.util.List;
import com.smerp.jwt.models.UserDto;
import com.smerp.model.admin.User;

public interface UserService {

	User save(UserDto user);

	List<User> findAll();

	void delete(long id);

	User findOne(String username);

	User findById(Long id);
}
