package com.linqcod.alfabankexchangeratesrestapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class AppConfig {

    @Bean(name = "date_bean")
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @Bean(name = "time_bean")
    public SimpleDateFormat simpleTimeFormat() {
        return new SimpleDateFormat("yyyy-MM-dd h");
    }

}
