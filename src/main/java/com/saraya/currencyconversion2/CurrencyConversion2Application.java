package com.saraya.currencyconversion2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class CurrencyConversion2Application {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversion2Application.class, args);
	}

}
