/**
 * 
 */
package br.tec.marco.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author marcoyf
 *
 */
// Netflix Ribbon provides load balancing, distributes the load between the various 
// instances of currency exchange service
// before Ribbon, we're able to call only a single instance of currency exchange microservice, 
// which is the one of port 8000
// now with Ribbon, we don't need the url attribute anymore
// @FeignClient(name = "currency-exchange-service", url = "localhost:8000")
// @FeignClient(name = "currency-exchange-service")
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	// @GetMapping("/currency-exchange/from/{from}/to/{to}")
	// http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(
			// since both beans have the same property name, we don't need to specify it in the PathVariable
			// @PathVariable("from") String from, @PathVariable("to") String to);
			@PathVariable String from, @PathVariable String to);
	
}
