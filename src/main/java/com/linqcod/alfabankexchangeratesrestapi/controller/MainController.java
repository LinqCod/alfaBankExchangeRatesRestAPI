package com.linqcod.alfabankexchangeratesrestapi.controller;

import com.linqcod.alfabankexchangeratesrestapi.service.gif.GifService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchangerates/api")
public class MainController {

    private GifService gifService;

    public MainController(GifService gifService) {
        this.gifService = gifService;
    }

    @GetMapping("/gif")
    String testGettingGif() {
        String result = gifService.getRandomGif("broke");
        return result;
    }

}
