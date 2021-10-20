package com.linqcod.alfabankexchangeratesrestapi;

import com.linqcod.alfabankexchangeratesrestapi.service.exchangeRates.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInit {

    private ExchangeRatesService exchangeRatesService;

    @Autowired
    public DataInit(ExchangeRatesService exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @PostConstruct
    public void initData() {
        exchangeRatesService.refreshCurrencyRates();
    }
}
