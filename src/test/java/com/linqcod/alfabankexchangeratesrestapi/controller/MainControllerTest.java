package com.linqcod.alfabankexchangeratesrestapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linqcod.alfabankexchangeratesrestapi.service.exchangeRates.OpenExchangeRatesServiceImpl;
import com.linqcod.alfabankexchangeratesrestapi.service.gif.GiphyGifServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Value("${giphy.rich}")
    private String richTag;
    @Value("${giphy.broke}")
    private String brokeTag;
    @Value("${giphy.error}")
    private String errorTag;

    private ObjectMapper mapper = new ObjectMapper();

}
