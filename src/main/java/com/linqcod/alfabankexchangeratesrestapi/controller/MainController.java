package com.linqcod.alfabankexchangeratesrestapi.controller;

import com.linqcod.alfabankexchangeratesrestapi.service.exchangeRates.ExchangeRatesService;
import com.linqcod.alfabankexchangeratesrestapi.service.gif.GifService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exchangerates/api")
public class MainController {

    private ExchangeRatesService exchangeRatesService;
    private GifService gifService;
    @Value("${giphy.rich}")
    private String richTag;
    @Value("${giphy.broke}")
    private String brokeTag;
    @Value("${giphy.equal}")
    private String equalTag;
    @Value("${giphy.error}")
    private String errorTag;

    public MainController(ExchangeRatesService exchangeRatesService, GifService gifService) {
        this.exchangeRatesService = exchangeRatesService;
        this.gifService = gifService;
    }

    @GetMapping("/getcurrencycodes")
    public List<String> getCurrencyCodes() {
        return exchangeRatesService.getCurrencyCodes();
    }

    @GetMapping("/gif/{code}")
    public ResponseEntity<Map> getGifByCurrencyCode(@PathVariable String code) {
        int keyForGifTag = 2021;
        String gifTag;
        if(code != null) {
            keyForGifTag = exchangeRatesService.getKeyForGifTag(code);
        }
        switch (keyForGifTag) {
            case 1:
                gifTag = richTag;
                break;
            case 0:
                gifTag = equalTag;
                break;
            case -1:
                gifTag = brokeTag;
                break;
            default:
                gifTag = errorTag;
                break;
        }

        return gifService.getRandomGif(gifTag);
    }

}
