package com.smerp.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smerp.model.master.TimeZone;

public interface TimeZoneRepository  extends JpaRepository<TimeZone,Integer> {

	TimeZone findById(int id);
}
