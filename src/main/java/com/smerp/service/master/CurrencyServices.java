package com.smerp.service.master;

import java.util.List;
import com.smerp.model.master.Currency;

public interface CurrencyServices {

	Currency save(Currency currency);

	List<Currency> findAll();

	Currency findById(int currencyId);

	void delete(int currencyId);
	
	Currency getInfo(int currencyId);
	
	Currency findByName(String name);
	
}
