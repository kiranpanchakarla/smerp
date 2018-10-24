package com.smerp.master.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smerp.master.model.Currency;


@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
	Currency findById(int id);
}
