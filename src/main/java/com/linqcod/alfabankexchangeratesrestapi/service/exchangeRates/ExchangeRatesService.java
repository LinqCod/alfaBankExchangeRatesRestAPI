package com.linqcod.alfabankexchangeratesrestapi.service.exchangeRates;

import java.util.List;

public interface ExchangeRatesService {
    List<String> getCurrencyCodes();
    int getKeyForGifTag(String currencyCode);
    void refreshCurrencyRates();
}
