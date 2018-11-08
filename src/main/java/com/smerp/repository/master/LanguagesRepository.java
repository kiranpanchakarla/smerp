package com.smerp.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smerp.model.master.Languages;

@Repository
public interface LanguagesRepository extends JpaRepository<Languages, Integer>{

	Languages findById(int id);
}