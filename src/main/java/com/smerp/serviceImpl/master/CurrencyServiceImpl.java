package com.smerp.serviceImpl.master;

import java.sql.Timestamp;
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
