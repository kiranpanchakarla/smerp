package com.smerp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)")
	User findByUsername(@Param("username") String username);

	List<User> findByCompanyId(Integer id);

	User findByUserId(Integer id);
   
}
