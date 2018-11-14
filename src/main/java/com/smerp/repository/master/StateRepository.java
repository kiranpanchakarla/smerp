package com.smerp.repository.master;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smerp.model.master.States;

public interface StateRepository extends JpaRepository<States, Integer> {
	List<States> findBycountryId(int contryId);

	States findById(int id);
}
