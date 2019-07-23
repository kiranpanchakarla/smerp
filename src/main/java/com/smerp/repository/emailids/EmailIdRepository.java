package com.smerp.repository.emailids;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.emailids.EmailId;

public interface EmailIdRepository extends JpaRepository<EmailId,Integer> {

	@Query("SELECT e.toEmail FROM EmailId e WHERE LOWER(e.moduleName)= LOWER(:name) and LOWER(e.operation) = LOWER(:status) ")
	String findToEmailIds(@Param("name") String name,@Param("status") String status);
	
	@Query("SELECT e.ccEmail FROM EmailId e WHERE LOWER(e.moduleName)= LOWER(:module) and LOWER(e.operation) = LOWER(:status) ")
	String findCCEmailIds(@Param("module") String module,@Param("status") String status);
	
	@Query("SELECT e.toEmailYML FROM EmailId e WHERE LOWER(e.moduleName)= LOWER(:name) and LOWER(e.operation) = LOWER(:status) ")
	String getToYMLEmailIds(@Param("name") String name,@Param("status") String status);
	
	/*@Query("SELECT e.bccEmail FROM EmailId e WHERE LOWER(e.moduleName)= LOWER(:bcc) and LOWER(e.operation) = LOWER(:status) ")
	String findBCCEmailIds(String bcc,String status);*/
}
