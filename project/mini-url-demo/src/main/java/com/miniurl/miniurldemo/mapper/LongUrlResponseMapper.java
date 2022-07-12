package com.miniurl.miniurldemo.mapper;

import com.miniurl.miniurldemo.entity.Url;
import com.miniurl.miniurldemo.response.LongUrlResponse;

public class LongUrlResponseMapper {
    
    public static LongUrlResponse mapper(Url url){

        LongUrlResponse response = new LongUrlResponse();

        response.setBigUrl(url.getBigUrl());
        response.setCode("00");
        response.setDescription("Sucesso");

        return response;
    }

    public static LongUrlResponse mapper(Url url, String code, String description){

        LongUrlResponse response = new LongUrlResponse();

        response.setBigUrl(url.getBigUrl());
        response.setCode(code);
        response.setDescription(description);

        return response;
    }

}
