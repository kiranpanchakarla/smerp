package com.smerp.repository.emailids;


import javax.validation.constraints.Email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.smerp.model.emailids.EmailId;

public interface EmailIdRepository extends JpaRepository<EmailId,Integer> {

	
	
	@Query("SELECT e.toEmail FROM EmailId e WHERE LOWER(e.moduleName)= LOWER(:name) and LOWER(e.operation) = LOWER(:status) ")
	String findToEmailIds(String name,String status);
	
	@Query("SELECT e.ccEmail FROM EmailId e WHERE LOWER(e.moduleName)= LOWER(:module) and LOWER(e.operation) = LOWER(:status) ")
	String findCCEmailIds(String module,String status);
	
	@Query("SELECT e.toEmailYML FROM EmailId e WHERE LOWER(e.moduleName)= LOWER(:name) and LOWER(e.operation) = LOWER(:status) ")
	String getToYMLEmailIds(String name,String status);
	
	/*@Query("SELECT e.bccEmail FROM EmailId e WHERE LOWER(e.moduleName)= LOWER(:bcc) and LOWER(e.operation) = LOWER(:status) ")
	String findBCCEmailIds(String bcc,String status);*/
}
