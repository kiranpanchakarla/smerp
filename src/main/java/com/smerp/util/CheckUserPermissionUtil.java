package com.smerp.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.User;


@Repository
@Transactional(value = TxType.REQUIRED)
public class CheckUserPermissionUtil {

private static final Logger logger = LogManager.getLogger(CheckUserPermissionUtil.class);
	
	@PersistenceContext    
	private EntityManager entityManager;
	

	public boolean checkUserPermission(Integer userId,Integer moduleId,Integer permissionId) {
		logger.info("usermission userId"+userId);
		String sql="select * from tbl_admin_usermodulepermission where module_id = "+moduleId+" and  permission_id = "+permissionId
				+"  and   user_id = " + userId;
		logger.info("sql-->" +sql);
		Query query = entityManager.createNativeQuery(sql);
	     List<Object[]>	list= query.getResultList();
	    if(list.size()>0) {
	    	return true;
	    }else {
	    	return false;
	    }
		
	}
	
	
	
	public User getUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	
}
