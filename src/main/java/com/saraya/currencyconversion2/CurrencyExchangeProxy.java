package com.saraya.currencyconversion2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.saraya.currencyconversion2.bean.CurrencyConversion;

//@FeignClient(name= "currency-exchange" , url="localhost:8000")
@FeignClient(name= "currency-exchange")
public interface CurrencyExchangeProxy {
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveValues(@PathVariable String from, @PathVariable String to);
	
	
	}
