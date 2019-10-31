package com.sela.hotelvendor;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
public class HotelService {

    public Integer getPriceByName(String hotelName) {
        return new Random().nextInt(1000);
    }
}
