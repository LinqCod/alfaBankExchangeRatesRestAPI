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
    public Gif getRandomGif(String tag) {
        Gif gifResult = null;

        try {
            Gson gson = new Gson();
            String dataJson = gson.toJson(gifClient.getRandomGif(apiKey, tag).getBody().get("data"));
            gifResult = gson.fromJson(dataJson, Gif.class);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return gifResult;
    }
}
