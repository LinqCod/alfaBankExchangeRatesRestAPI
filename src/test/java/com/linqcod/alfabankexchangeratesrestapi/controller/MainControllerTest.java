package com.linqcod.alfabankexchangeratesrestapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linqcod.alfabankexchangeratesrestapi.service.exchangeRates.OpenExchangeRatesServiceImpl;
import com.linqcod.alfabankexchangeratesrestapi.service.gif.GiphyGifServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebMvcTest(MainController.class)
public class MainControllerTest {

    private static final String BASE_URL = "/exchangerates/api";

    @Value("${giphy.rich}")
    private String richTag;
    @Value("${giphy.broke}")
    private String brokeTag;
    @Value("${giphy.equal}")
    private String equalTag;
    @Value("${giphy.error}")
    private String errorTag;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OpenExchangeRatesServiceImpl openExchangeRatesService;
    @MockBean
    private GiphyGifServiceImpl giphyGifService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testReturnListOfCurrencyCodes() throws Exception {
        List<String> testCurrencyCodes = new ArrayList<>();
        testCurrencyCodes.add("CurrencyCode1");
        testCurrencyCodes.add("CurrencyCode2");

        Mockito.when(openExchangeRatesService.getCurrencyCodes())
                .thenReturn(testCurrencyCodes);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/getcurrencycodes"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]", Matchers.is("CurrencyCode1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]", Matchers.is("CurrencyCode2")));
    }

    @Test
    public void testReturnEmptyListOfCurrencyCodes() throws Exception {
        Mockito.when(openExchangeRatesService.getCurrencyCodes())
                .thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/getcurrencycodes")
                        .content(mapper.writeValueAsString(null))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").doesNotExist());
    }

    @Test
    public void testReturnRandomRichGif() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("resultMessage", this.richTag);

        ResponseEntity<Map> responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        Mockito.when(openExchangeRatesService.getKeyForGifTag(ArgumentMatchers.anyString()))
                .thenReturn(1);
        Mockito.when(giphyGifService.getRandomGif(this.richTag))
                        .thenReturn(responseEntity);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/gif/currencyCode"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resultMessage", Matchers.is(this.richTag)));
    }

    @Test
    public void testReturnRandomEqualGif() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("resultMessage", this.equalTag);

        ResponseEntity<Map> responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        Mockito.when(openExchangeRatesService.getKeyForGifTag(ArgumentMatchers.anyString()))
                .thenReturn(0);
        Mockito.when(giphyGifService.getRandomGif(this.equalTag))
                .thenReturn(responseEntity);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/gif/currencyCode"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resultMessage", Matchers.is(this.equalTag)));
    }

    @Test
    public void testReturnRandomBrokeGif() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("resultMessage", this.brokeTag);

        ResponseEntity<Map> responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        Mockito.when(openExchangeRatesService.getKeyForGifTag(ArgumentMatchers.anyString()))
                .thenReturn(-1);
        Mockito.when(giphyGifService.getRandomGif(this.brokeTag))
                .thenReturn(responseEntity);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/gif/currencyCode"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resultMessage", Matchers.is(this.brokeTag)));
    }

    @Test
    public void testReturnRandomErrorGif() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("resultMessage", this.errorTag);

        ResponseEntity<Map> responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        Mockito.when(openExchangeRatesService.getKeyForGifTag(ArgumentMatchers.anyString()))
                .thenReturn(2021);
        Mockito.when(giphyGifService.getRandomGif(this.errorTag))
                .thenReturn(responseEntity);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/gif/currencyCode"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resultMessage", Matchers.is(this.errorTag)));
    }
}
