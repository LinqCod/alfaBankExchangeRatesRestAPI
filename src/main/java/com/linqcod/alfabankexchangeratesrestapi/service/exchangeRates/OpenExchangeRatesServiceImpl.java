package com.linqcod.alfabankexchangeratesrestapi.service.exchangeRates;

import com.linqcod.alfabankexchangeratesrestapi.client.exchangeRates.ExchangeRatesClient;
import com.linqcod.alfabankexchangeratesrestapi.model.ExchangeRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class OpenExchangeRatesServiceImpl implements ExchangeRatesService {

    private ExchangeRatesClient exchangeRatesClient;

    private ExchangeRates yesterdayRates;
    private ExchangeRates currentRates;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    @Value("${openexchangerates.app.id}")
    private String appId;

    @Autowired
    public OpenExchangeRatesServiceImpl(
            ExchangeRatesClient exchangeRatesClient,
            @Qualifier("date_bean") SimpleDateFormat simpleDateFormat,
            @Qualifier("time_bean") SimpleDateFormat simpleTimeFormat
    ) {
        this.exchangeRatesClient = exchangeRatesClient;
        this.dateFormat = simpleDateFormat;
        this.timeFormat = simpleTimeFormat;
    }

    @Override
    public List<String> getCurrencyCodes() {
        List<String> codes = null;
        if(currentRates.getRates() != null) {
            codes = new ArrayList<>(currentRates.getRates().keySet());
        }

        return codes;
    }

    @Override
    public int getKeyForGifTag(String currencyCode) {
        refreshCurrencyRates();

        return compareCurrencyRates(currencyCode);
    }

    @Override
    public void refreshCurrencyRates() {
        long currentTime = System.currentTimeMillis();
        refreshYesterdayRates(currentTime);
        refreshCurrentRates(currentTime);
    }

    private void refreshYesterdayRates(long currentTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        String yesterdayDate = dateFormat.format(calendar.getTime());

        if(yesterdayRates == null ||
                !dateFormat.format(Long.valueOf(yesterdayRates.getTimestamp()) * 1000)
                        .equals(yesterdayDate)) {
            yesterdayRates = exchangeRatesClient.getHistoricalRates(yesterdayDate, appId);
        }
    }

    private void refreshCurrentRates(long currentTime) {
        if(currentRates == null ||
                !timeFormat.format(Long.valueOf(currentRates.getTimestamp()) * 1000)
                        .equals(timeFormat.format(currentTime))) {
            currentRates = exchangeRatesClient.getLatestRates(appId);
        }
    }

    private int compareCurrencyRates(String currencyCode) {
        Double yesterdayCoefficient = getCoefficient(yesterdayRates, currencyCode);
        Double currentCoefficient = getCoefficient(currentRates, currencyCode);

        return yesterdayCoefficient != null && currentCoefficient != null
                ? Double.compare(currentCoefficient, yesterdayCoefficient)
                : 2021;
    }

    private Double getCoefficient(ExchangeRates rates, String currencyCode) {
        Double rate = null;

        if(rates != null && rates.getRates() != null) {
            rate = rates.getRates().get(currencyCode);
        }

        return rate;
    }
}
