package com.smerp.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.smerp.model.admin.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

	User findByUsername(String username);

	List<User> findByCompanyId(Integer id);

	User findByUserId(Integer id);
   
}
