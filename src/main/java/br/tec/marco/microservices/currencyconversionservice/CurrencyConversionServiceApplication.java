package br.tec.marco.microservices.currencyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
// using Netflix Feign to make it easy to call the currency exchange service 
@EnableFeignClients("br.tec.marco.microservices.currencyconversionservice")
// Service Registration with the Eureka Naming Server
// then currency conversion service will use Service Discovery to call currency exchange service
@EnableDiscoveryClient
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}

	// method used by Spring Sleuth, which assigns a unique ID for each incoming request
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
