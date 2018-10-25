package com.smerp.master.service;

import java.util.List;

import com.smerp.model.master.Currency;

public interface CurrencyServices {
	
	Currency save(Currency currency);
	List<Currency> currencyList();
	Currency findById(int id);
    void delete(int id);
}
