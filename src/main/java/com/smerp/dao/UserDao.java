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

	
	@Query("SELECT username FROM User")
	List<String> findAllUsername();

	
	@Query("SELECT u FROM User u WHERE LOWER(CONCAT(firstname,' ',lastname)) = LOWER(:username)")
	User findByName(String username);
	
	@Query("SELECT u FROM User u WHERE enabled=:enabled order by updatedAt desc")
	   List<User>  findByIsActive(@Param("enabled") Boolean enabled);

	
	@Query("SELECT CONCAT(firstname,' ',lastname) FROM User WHERE enabled=:enabled")
	List<String> findFirstNames(@Param("enabled") Boolean enabled);
   
}
