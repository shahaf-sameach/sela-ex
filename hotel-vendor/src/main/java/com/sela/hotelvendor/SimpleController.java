package com.sela.hotelvendor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/api/v1/hotel")
public class SimpleController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/price")
    public Mono<ResponseEntity<Integer>> getHotelPrice(@RequestParam("hotelName") String hotelName) {
        log.info("got price request for hotel {}", hotelName);
        return Mono.delay(Duration.of(new Random().nextInt(100), ChronoUnit.MILLIS))
                .map(i -> hotelService.getPriceByName(hotelName))
                .map(ResponseEntity::ok);
    }
}
