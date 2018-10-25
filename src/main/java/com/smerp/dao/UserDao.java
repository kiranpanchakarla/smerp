package com.smerp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.User;



@Repository
public interface UserDao extends CrudRepository<User, Long> {

	com.smerp.model.admin.User findByUserName(String userName);
   
}
