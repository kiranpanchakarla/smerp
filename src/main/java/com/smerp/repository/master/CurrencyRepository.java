package com.smerp.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smerp.model.master.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
	Currency findById(int id);

}
