package com.smerp.service;

import java.util.List;
import com.smerp.jwt.models.UserDto;
import com.smerp.model.admin.Company;
import com.smerp.model.admin.User;

public interface UserService {

	User save(User user);

	List<User> findAll();

	void delete(long id);

	User findOne(String username);

	User findById(Long id);

	List<User>  findByUsersByCompany(Company company);

	User findByUsername(String username);
}
