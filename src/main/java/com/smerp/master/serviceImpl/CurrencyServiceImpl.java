package com.smerp.master.serviceImpl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.master.repository.CurrencyRepository;
import com.smerp.master.service.CurrencyServices;
import com.smerp.model.master.Currency;

@Service
public class CurrencyServiceImpl implements CurrencyServices {

	@Autowired
	CurrencyRepository currencyRepository;

	public Currency save(Currency currency) {
		currency.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		currency.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		currencyRepository.save(currency);
		return currency;
	}

	@Override
	public List<Currency> currencyList() {
		return currencyRepository.findAll();
	}

	public Currency findById(int id) {
		return currencyRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		currencyRepository.deleteById(id);
	}

}
