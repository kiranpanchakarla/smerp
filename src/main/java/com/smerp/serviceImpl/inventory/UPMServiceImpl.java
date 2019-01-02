package com.smerp.serviceImpl.inventory;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.Permission;
import com.smerp.model.admin.UPM;
import com.smerp.service.inventory.UPMService;

@Repository
@Transactional(value = TxType.REQUIRED)
public class UPMServiceImpl implements UPMService {
	
	private static final Logger logger = LogManager.getLogger(UPMServiceImpl.class);
	
	@PersistenceContext    
	private EntityManager entityManager;

	@Override
	public List<UPM> findAll(Integer id) {
		logger.info("usermission query method"+id);
		
		String sql="select \r\n" + 
				" mp.module_id\r\n" + 
				",mp.module_name\r\n" + 
				",mp.permission_id\r\n" + 
				",mp.permission_name\r\n" + 
				",ump.module_id ump_module_id\r\n" + 
				",ump.permission_id ump_permission_id\r\n" + 
				",case when ump.module_id IS NULL and ump.permission_id  IS NULL then 'Unchecked'\r\n" + 
				" else 'Checked' end as user_access\r\n" + 
				" ,ump.id\r\n" + 
				" from (select am.module_id,am.module_name,ap.permission_id,ap.permission_name  from tbl_admin_module_permission amp" + 
				" INNER JOIN tbl_admin_module am on amp.module_id= am.module_id   INNER JOIN  tbl_admin_permission ap " + 
				" on amp.permission_id=ap.permission_id  order by am.module_id ) mp\r\n" + 
				"left outer join (select id,user_id,module_id,permission_id from tbl_admin_usermodulepermission  where user_id=:user_id) ump on mp.module_id= ump.module_id and ump.permission_id=mp.permission_id\r\n" + 
				"group by  \r\n" + 
				" mp.module_id\r\n" + 
				",mp.module_name\r\n" + 
				",mp.permission_id\r\n" + 
				",mp.permission_name\r\n" + 
				",ump.module_id \r\n" + 
				",ump.permission_id\r\n" + 
				",ump.id  order by mp.module_id ";
		
		logger.info("sql-->" +sql);
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("user_id", id);
	     List<Object[]>	list= query.getResultList();
	     System.out.println(list.size());
	     List<UPM>	upmlist= new ArrayList<>();
	     for(Object[] tuple : list) {
	    	 UPM upm = new UPM();
		    	 upm.setModule_id(tuple[0] == null ? 0 : (Integer) tuple[0]);
		    	 upm.setModule_name(tuple[1]== null ? "0" : (String) tuple[1]);
		    	 upm.setPermssion_id(tuple[2] == null ? 0 : (Integer) tuple[2]);
		    	 upm.setPermission_name(tuple[3]== null ? "0" : (String) tuple[3]);
		    	 upm.setId(tuple[7]== null ? 0 : (Integer) tuple[7]);
		    	 upm.setUser_access(tuple[6]== null ? "0" : (String) tuple[6]);
		    	 upmlist.add(upm);
	    	
	    	}
	    logger.info("upmlist size--------->"+upmlist.size()); 
		return upmlist;
	}
	
	
	
	/*@Override
	public List<ModulePermission> findModulePermission() {
		
		String sql="select am.module_id,am.module_name,ap.permission_id,ap.permission_name  from tbl_admin_module_permission amp INNER JOIN tbl_admin_module am on amp.module_id= am.module_id   INNER JOIN  tbl_admin_permission ap \r\n" + 
				"on amp.permission_id=ap.permission_id  order by am.module_id";
		
		 System.out.println("sql-->" + sql);
		Query query = entityManager.createNativeQuery(sql);
	     List<Object[]>	list= query.getResultList();
	     System.out.println("size-->"+list.size());
	     List<ModulePermission>	upmlist= new ArrayList<>();
	     for(Object[] tuple : list) {
	    	 ModulePermission mp = new ModulePermission();
			mp.setModule_id(tuple[0] == null ? 0 : (Integer) tuple[0]);
			mp.setModule_name(tuple[1] == null ? "0" : (String) tuple[1]);
			mp.setPermssion_id(tuple[2] == null ? 0 : (Integer) tuple[2]);
			mp.setPermission_name(tuple[3]== null ? "0" : (String) tuple[3]);
		    	 upmlist.add(mp);
	    	}
	    logger.info("upmlist--------->"+upmlist); 
	    logger.info("upmlist size--------->"+upmlist.size()); 
		return upmlist;
	}*/


	
	
	
	

}
