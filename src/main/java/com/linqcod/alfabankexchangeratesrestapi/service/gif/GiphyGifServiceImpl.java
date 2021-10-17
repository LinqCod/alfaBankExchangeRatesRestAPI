package com.linqcod.alfabankexchangeratesrestapi.service.gif;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.linqcod.alfabankexchangeratesrestapi.client.gif.GifClient;
import com.linqcod.alfabankexchangeratesrestapi.model.Gif;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GiphyGifServiceImpl implements GifService {
    private GifClient gifClient;
    @Value("${giphy.api.key}")
    private String apiKey;

    public GiphyGifServiceImpl(GifClient gifClient) {
        this.gifClient = gifClient;
    }

    @Override
    public String getRandomGif(String tag) {
        String dataJson = null;
        Gson gson = new Gson();
        try {

            dataJson = gson.toJson(gifClient.getRandomGif(apiKey, tag).getBody().get("data"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return dataJson;
    }
}
