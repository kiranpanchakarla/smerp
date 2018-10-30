package com.smerp.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smerp.model.master.HSNCode;

@Repository
public interface HsnRepository extends JpaRepository<HSNCode, Integer> {

}
