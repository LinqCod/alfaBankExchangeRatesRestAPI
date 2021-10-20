package com.linqcod.alfabankexchangeratesrestapi.service;

import com.linqcod.alfabankexchangeratesrestapi.client.exchangeRates.FeignOpenExchangeRatesClient;
import com.linqcod.alfabankexchangeratesrestapi.model.ExchangeRates;
import com.linqcod.alfabankexchangeratesrestapi.service.exchangeRates.OpenExchangeRatesServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class OpenExchangeRatesServiceImplTest {

    @Value("${openexchangerates.base}")
    private String base;
    @Autowired
    private OpenExchangeRatesServiceImpl openExchangeRatesService;
    @MockBean
    private FeignOpenExchangeRatesClient feignOpenExchangeRatesClient;

    private ExchangeRates currentRates;
    private ExchangeRates yesterdayRates;

    {
        int time = 1634739688;
        this.currentRates = new ExchangeRates();
        this.currentRates.setTimestamp(time);
        this.currentRates.setBase(this.base);
        Map<String, Double> currentRatesMap = new HashMap<>();
        currentRatesMap.put("CR1", 0.3);
        currentRatesMap.put("CR2", 0.7);
        currentRatesMap.put("CR3", 3.0);
        currentRatesMap.put(this.base, 70.9);
        this.currentRates.setRates(currentRatesMap);

        time = 1634653288;
        this.yesterdayRates = new ExchangeRates();
        this.yesterdayRates.setTimestamp(time);
        this.yesterdayRates.setBase(this.base);
        Map<String, Double> yesterdayRatesMap = new HashMap<>();
        yesterdayRatesMap.put("CR1", 0.3);
        yesterdayRatesMap.put("CR2", 0.9);
        yesterdayRatesMap.put("CR3", 2.8);
        yesterdayRatesMap.put(this.base, 70.9);
        this.yesterdayRates.setRates(yesterdayRatesMap);
    }

    @Test
    public void testPositiveChanges() {
        Mockito.when(feignOpenExchangeRatesClient.getLatestRates(ArgumentMatchers.anyString()))
                .thenReturn(this.currentRates);
        Mockito.when(feignOpenExchangeRatesClient.getHistoricalRates(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(this.yesterdayRates);
        int result = openExchangeRatesService.getKeyForGifTag("CR2");
        Assertions.assertEquals(-1, result);
    }

    @Test
    public void testNegativeChanges() {
        Mockito.when(feignOpenExchangeRatesClient.getLatestRates(ArgumentMatchers.anyString()))
                .thenReturn(this.currentRates);
        Mockito.when(feignOpenExchangeRatesClient.getHistoricalRates(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(this.yesterdayRates);
        int result = openExchangeRatesService.getKeyForGifTag("CR3");
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testNoChanges() {
        Mockito.when(feignOpenExchangeRatesClient.getLatestRates(ArgumentMatchers.anyString()))
                .thenReturn(this.currentRates);
        Mockito.when(feignOpenExchangeRatesClient.getHistoricalRates(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(this.yesterdayRates);
        int result = openExchangeRatesService.getKeyForGifTag("CR1");
        Assertions.assertEquals(0, result);
    }

    @Test
    public void testUnExistCurrencyCodeOnInput() {
        Mockito.when(feignOpenExchangeRatesClient.getLatestRates(ArgumentMatchers.anyString()))
                .thenReturn(this.currentRates);
        Mockito.when(feignOpenExchangeRatesClient.getHistoricalRates(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(this.yesterdayRates);
        int result = openExchangeRatesService.getKeyForGifTag("UNEXIST");
        Assertions.assertEquals(2021, result);
    }
}
