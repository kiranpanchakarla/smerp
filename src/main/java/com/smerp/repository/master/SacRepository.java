package com.smerp.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smerp.model.master.SACCode;

@Repository
public interface SacRepository extends JpaRepository<SACCode, Integer> {

}
