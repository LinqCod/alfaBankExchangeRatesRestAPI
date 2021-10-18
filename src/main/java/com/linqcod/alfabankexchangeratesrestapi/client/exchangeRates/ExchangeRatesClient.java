package com.linqcod.alfabankexchangeratesrestapi.client.exchangeRates;

import com.linqcod.alfabankexchangeratesrestapi.model.ExchangeRates;

public interface ExchangeRatesClient {
    ExchangeRates getLatestRates(String appId);
    ExchangeRates getHistoricalRates(String date, String appId);
}
