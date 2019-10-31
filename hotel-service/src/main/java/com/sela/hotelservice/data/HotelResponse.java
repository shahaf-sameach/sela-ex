package com.sela.hotelservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {

    private String providerName;
    private String hotelName;
    private Integer price;

    public static HotelResponse from(PriceResponse priceResponse) {
        HotelResponse hotelResponse = new HotelResponse();
        hotelResponse.setHotelName(priceResponse.getHotelName());
        hotelResponse.setProviderName(priceResponse.getProviderName());
        hotelResponse.setPrice(priceResponse.getPrice());
        return hotelResponse;
    }

    // ...
}
