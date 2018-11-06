package com.smerp.serviceImpl.master;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.master.Currency;
import com.smerp.repository.master.CurrencyRepository;
import com.smerp.service.master.CurrencyServices;

@Service
public class CurrencyServiceImpl implements CurrencyServices {

	@Autowired
	CurrencyRepository currencyRepository;

	public Currency save(Currency currency) {
		currencyRepository.save(currency);
		return currency;
	}

	@Override
	public List<Currency> findAll() {
		return currencyRepository.findAll();
	}

	public Currency findById(int currencyId) {
		return currencyRepository.findById(currencyId);
	}

	@Override
	public void delete(int currencyId) {
		currencyRepository.deleteById(currencyId);
	}

	@Override
	public Currency getInfo(int currencyId) {
		 
		return currencyRepository.findById(currencyId);
	}

}
