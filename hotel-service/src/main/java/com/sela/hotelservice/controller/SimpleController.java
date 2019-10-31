package com.sela.hotelservice.controller;

import com.sela.hotelservice.util.HotelResponseUtil;
import com.sela.hotelservice.service.HotelService;
import com.sela.hotelservice.data.HotelResponse;
import com.sela.hotelservice.data.PriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/hotel")
public class SimpleController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/best")
    public Mono<ResponseEntity<HotelResponse>> getBestOffer(@RequestParam("hotelName") String hotelName) {
        log.info("got request for hotel {}", hotelName);
        return hotelService.getAll(hotelName)
                .doOnNext(response -> log.info("got response {}", response))
                .filter(PriceResponse::isSuccess)
                .map(HotelResponse::from)
                .reduce(HotelResponseUtil::min)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
