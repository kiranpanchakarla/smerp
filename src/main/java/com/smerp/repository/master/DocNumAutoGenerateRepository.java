package com.smerp.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smerp.model.master.DocNumAutoGenerate;

public interface DocNumAutoGenerateRepository extends JpaRepository<DocNumAutoGenerate, Integer> {
	
	/*@Query("SELECT d FROM DocNumAutoGenerate d WHERE LOWER(d.docType) = LOWER(:docType)")
	DocNumAutoGenerate getCount(String docType);*/
	
	DocNumAutoGenerate findByDocType(String docType);
	
}
