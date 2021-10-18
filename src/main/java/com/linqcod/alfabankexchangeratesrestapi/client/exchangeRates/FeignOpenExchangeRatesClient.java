package com.linqcod.alfabankexchangeratesrestapi.client.exchangeRates;

import com.linqcod.alfabankexchangeratesrestapi.model.ExchangeRates;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.cloud.openfeign.FeignClient(name = "exchangeRatesClient", url = "${openexchangerates.url.general}")
public interface FeignOpenExchangeRatesClient extends ExchangeRatesClient {
    @Override
    @GetMapping("/latest.json")
    ExchangeRates getLatestRates(@RequestParam("app_id") String appId);

    @Override
    @GetMapping("/historical/{date}.json")
    ExchangeRates getHistoricalRates(
            @PathVariable String date,
            @RequestParam("app_id") String appId
    );
}
