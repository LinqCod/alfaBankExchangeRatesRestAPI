package com.linqcod.alfabankexchangeratesrestapi;

import com.linqcod.alfabankexchangeratesrestapi.service.gif.GifService;
import com.linqcod.alfabankexchangeratesrestapi.service.gif.GiphyGifServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlfaBankExchangeRatesRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlfaBankExchangeRatesRestApiApplication.class, args);
    }

}
