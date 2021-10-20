package com.linqcod.alfabankexchangeratesrestapi.service;

import com.linqcod.alfabankexchangeratesrestapi.client.gif.FeignGiphyGifClient;
import com.linqcod.alfabankexchangeratesrestapi.service.gif.GiphyGifServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class GiphyGifServiceImplTest {

    @Value("${giphy.error.msg}")
    private String errorMsg;

    @Autowired
    private GiphyGifServiceImpl giphyGifService;
    @MockBean
    private FeignGiphyGifClient feignGiphyGifClient;

    @Test
    public void testGetRandomGif() {
        ResponseEntity<Map> testEntity = new ResponseEntity<>(new HashMap<String, String>(), HttpStatus.OK);
        Mockito.when(feignGiphyGifClient.getRandomGif(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(testEntity);

        ResponseEntity<Map> resultEntity = giphyGifService.getRandomGif("tagThatDoesntExist");
        Assertions.assertEquals(errorMsg, resultEntity.getBody().get("resultMessage"));
    }

}
