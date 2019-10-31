package com.sela.hotelservice.util;


import com.sela.hotelservice.data.HotelResponse;

public class HotelResponseUtil {

    public static HotelResponse min(HotelResponse a, HotelResponse b) {
        if (a.getPrice() >= b.getPrice())
            return b;
        else
            return a;
    }

}
