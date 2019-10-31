package com.sela.hotelservice.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PriceResponse {

    private Boolean failed;
    private Integer price;
    private String providerName;
    private String hotelName;

    public static PriceResponse success(String providerName, String hotelName, Integer price) {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setFailed(false);
        priceResponse.setProviderName(providerName);
        priceResponse.setHotelName(hotelName);
        priceResponse.setPrice(price);
        return priceResponse;
    }

    public static PriceResponse failure(String providerName, String hotelName) {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setFailed(true);
        priceResponse.setProviderName(providerName);
        priceResponse.setHotelName(hotelName);
        return priceResponse;
    }

    public boolean isSuccess() {
        return !failed;
    }

}
