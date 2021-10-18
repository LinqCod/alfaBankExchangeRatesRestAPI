package com.linqcod.alfabankexchangeratesrestapi;

import com.linqcod.alfabankexchangeratesrestapi.service.exchangeRates.ExchangerRatesService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInit {

    private ExchangerRatesService exchangerRatesService;

    public DataInit(ExchangerRatesService exchangerRatesService) {
        this.exchangerRatesService = exchangerRatesService;
    }

    @PostConstruct
    public void initData() {
        exchangerRatesService.refreshCurrencyRates();
    }
}
