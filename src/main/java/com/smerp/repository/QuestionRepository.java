package com.smerp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smerp.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
