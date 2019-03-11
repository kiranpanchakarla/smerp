package com.smerp.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smerp.dao.UserDao;
import com.smerp.model.admin.Company;
import com.smerp.model.admin.Role;
import com.smerp.model.admin.User;
import com.smerp.service.UserService;
import com.smerp.service.master.RoleService;
import com.smerp.util.RandomUtil;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			// authorities.add(new SimpleGrantedAuthority(role.getName()));
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		// return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override

	public User save(User user) {
		try {
			logger.info("inside userservice impl save method");
			
			user.setActivationId("InActive");
			//user.setPlant("test");
			if(user.getUserId()!=null) {
			
				if(user.getTempPassword()!=null && !user.getTempPassword().equals("")) {
				user.setPassword(bcryptEncoder.encode(user.getTempPassword()));
			   }
				
			}else {
				user.setPassword(bcryptEncoder.encode("Welcome"));
			}
			
			user.setCompany(getComapnyIdFromSession());
			//user.setUsername(RandomUtil.referenceId());
			String roleId = user.getRolesDt();
			Role role = roleService.findById(Long.parseLong(roleId));
			Set<Role> roles = new HashSet<>();
			roles.add(role);
			user.setRoles(roles);
			userDao.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDao.save(user);
	}

	@Override
	public List<User> findByUsersByCompany(Company company) {
		// TODO Auto-generated method stub
		return userDao.findByCompanyId(company.getId());
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

	private Company getComapnyIdFromSession() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getCompany();
	}

	@Override
	public void delete(int id) {
		logger.info("Inside userserviceimpl delete method" + id);
		User user = userDao.findByUserId(id);
		user.setEnabled(false);
		userDao.save(user);
	}

	@Override
	public User findById(int id) {
		User user=userDao.findByUserId(id);
		return user;
	}

	@Override
	public Map<Long, String> rolesMap(Set<Role> set) {
		 Map<Long, String> map=new LinkedHashMap<>();
		 for (Role role : set) {
			 map.put(role.getId(), role.getName());
		}
		List<Role> list=roleService.findAll();
		for (Role role : list) {
			if(!map.containsKey(role.getId())) {
				map.put(role.getId(), role.getName());
			}
		}
		logger.info("map---------->"+map);
		return map;
	}

	@Override
	public List<String> findAllUsername() {
		// TODO Auto-generated method stub
		return userDao.findAllUsername();
	}

	@Override
	public User findByName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByName(username);
	}

	 public List<User> findByIsActive() {
	        return userDao.findByIsActive(true);
	    }

	@Override
	public List<String> findFirstNames() {
		// TODO Auto-generated method stub
		return userDao.findFirstNames(true);
	}
	
	@Override
	public boolean checkCurrentPwd(String currentPwd,String enterPwd) {
		boolean check = bcryptEncoder.matches(enterPwd, currentPwd);
		return check;
	}

	public static void main(String[] args) {
		BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
		System.out.println(bcryptEncoder.matches("Welcome", "$2a$10$DLmkngFSI/w95Bv1TLtn1e7FXwXu1COXCjqS9ocago5EZCPuEdzaC")); // true
	}


}
