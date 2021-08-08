package com.saraya.currencyconversion2.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.saraya.currencyconversion2.CurrencyExchangeProxy;
import com.saraya.currencyconversion2.bean.CurrencyConversion;

@RestController
public class CurrenConversionController {
	private Logger logger = LoggerFactory.getLogger(CurrenConversionController.class);

	@Autowired
	private CurrencyExchangeProxy proxy;
	
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
			@PathVariable String to, @PathVariable BigDecimal quantity) {
		logger.info("retrieveExchangeValue called with {} to {}", from, to );	

		HashMap<String ,String> uriVariables =  new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/USD/to/CFA", CurrencyConversion.class,
			uriVariables);
		CurrencyConversion currencyConversion = responseEntity.getBody();
		return new CurrencyConversion(currencyConversion.getId() ,
				from , to , 
				quantity ,
				currencyConversion.getConversionMultiple() ,
				quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment()+ " "+ "template");
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionfeign(@PathVariable String from,
			@PathVariable String to, @PathVariable BigDecimal quantity) {
		
		CurrencyConversion currencyConversion = proxy.retrieveValues(from, to);
		return new CurrencyConversion(currencyConversion.getId() ,
				from , to , 
				quantity ,
				currencyConversion.getConversionMultiple() ,
				quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment()+ " "+ "feign");
	}
	
	
	
	
	
	

}
