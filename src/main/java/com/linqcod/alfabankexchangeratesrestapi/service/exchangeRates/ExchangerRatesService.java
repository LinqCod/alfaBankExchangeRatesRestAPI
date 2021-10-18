package com.linqcod.alfabankexchangeratesrestapi.service.exchangeRates;

import java.util.List;

public interface ExchangerRatesService {
    List<String> getCurrencyCodes();
    int getKeyForGifTag(String currencyCode);
}
