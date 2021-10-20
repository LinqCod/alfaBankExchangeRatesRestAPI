package com.linqcod.alfabankexchangeratesrestapi;

import com.linqcod.alfabankexchangeratesrestapi.service.exchangeRates.ExchangeRatesService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInit {

    private ExchangeRatesService exchangeRatesService;

    public DataInit(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @PostConstruct
    public void initData() {
        exchangeRatesService.refreshCurrencyRates();
    }
}
