package com.smerp.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.smerp.model.master.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
	
	Currency findById(int id);
	
	@Query("SELECT c FROM Currency c WHERE LOWER(c.name) = LOWER(:name)")
	Currency findByName(@Param("name") String name);

}
