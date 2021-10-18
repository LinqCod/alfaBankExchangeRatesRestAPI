package com.linqcod.alfabankexchangeratesrestapi.service.gif;

import com.google.gson.Gson;
import com.linqcod.alfabankexchangeratesrestapi.client.gif.GifClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GiphyGifServiceImpl implements GifService {
    private GifClient gifClient;
    @Value("${giphy.api.key}")
    private String apiKey;
    @Value("${giphy.rich.msg}")
    private String richMsg;
    @Value("${giphy.broke.msg}")
    private String brokeMsg;
    @Value("${giphy.equal.msg}")
    private String equalMsg;
    @Value("${giphy.error.msg}")
    private String errorMsg;

    public GiphyGifServiceImpl(GifClient gifClient) {
        this.gifClient = gifClient;
    }

    @Override
    public ResponseEntity<Map> getRandomGif(String tag) {
        ResponseEntity<Map> result = gifClient.getRandomGif(apiKey, tag);
        String resultMsg;
        switch (tag) {
            case "rich":
                resultMsg = richMsg;
                break;
            case "broke":
                resultMsg = brokeMsg;
                break;
            case "equal":
                resultMsg = equalMsg;
                break;
            default:
                resultMsg = errorMsg;
                break;

        }

        result.getBody().put("resultMessage", resultMsg);
        return result;
    }
}
