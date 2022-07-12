package com.miniurl.miniurldemo.mapper;

import com.miniurl.miniurldemo.entity.Url;
import com.miniurl.miniurldemo.response.LongUrlResponse;

public class LongUrlResponseMapper {
    
    /**
     * Creation of success responses
     * @param url - Url - Url information
     * @return - Success response
     */
    public static LongUrlResponse mapper(Url url){

        LongUrlResponse response = new LongUrlResponse();

        response.setBigUrl(url.getBigUrl());
        response.setCode("00");
        response.setDescription("Sucesso");

        return response;
    }

    /**
     * Creation of error responses
     * @param url - Url - Url information
     * @param code - String - Error code
     * @param description - String - Error description
     * @return - Error response
     */
    public static LongUrlResponse mapper(Url url, String code, String description){

        LongUrlResponse response = new LongUrlResponse();

        response.setBigUrl(url.getBigUrl());
        response.setCode(code);
        response.setDescription(description);

        return response;
    }

}
