package com.smerp.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.smerp.model.master.Languages;

@Repository
public interface LanguagesRepository extends JpaRepository<Languages, Integer>{

	Languages findById(int id);
	
	@Query("SELECT l FROM Languages l WHERE LOWER(l.name) = LOWER(:name)")
	Languages findByName(@Param("name") String name);
}
