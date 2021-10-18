package com.linqcod.alfabankexchangeratesrestapi.service.gif;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface GifService {
    ResponseEntity<Map> getRandomGif(String tag);
}
