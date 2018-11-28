package com.smerp.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smerp.model.admin.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

}
