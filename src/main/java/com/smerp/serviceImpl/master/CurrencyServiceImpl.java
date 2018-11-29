package com.smerp.serviceImpl.master;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.master.Currency;
import com.smerp.repository.master.CurrencyRepository;
import com.smerp.service.master.CurrencyServices;

@Service
public class CurrencyServiceImpl implements CurrencyServices {

	private static final Logger logger = LogManager.getLogger(CurrencyServiceImpl.class);

	@Autowired
	CurrencyRepository currencyRepository;

	public Currency save(Currency currency) {
		logger.info("inside CurrencyServiceImpl save method");
		currencyRepository.save(currency);
		return currency;
	}

	@Override
	public List<Currency> findAll() {
		logger.info("inside CurrencyServiceImpl findAll method");
		return currencyRepository.findAll();
	}

	public Currency findById(int currencyId) {
		logger.info("inside CurrencyServiceImpl findById method");
		return currencyRepository.findById(currencyId);
	}

	@Override
	public void delete(int currencyId) {
		logger.info("inside CurrencyServiceImpl delete method");
		currencyRepository.deleteById(currencyId);
	}

	@Override
	public Currency getInfo(int currencyId) {
		logger.info("inside CurrencyServiceImpl getInfo method");
		return currencyRepository.findById(currencyId);
	}

	@Override
	public Currency findByName(String name) {
		 
		return currencyRepository.findByName(name);
	}

}
