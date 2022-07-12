package com.miniurl.miniurldemo.mapper;

import com.miniurl.miniurldemo.entity.Url;
import com.miniurl.miniurldemo.response.MiniUrlResponse;

public class MiniUrlResponseMapper {
    
    /**
     * Creation of success responses
     * @param url - Url - Url information
     * @return - Success response
     */
    public static MiniUrlResponse mapper(Url url){

        MiniUrlResponse response = new MiniUrlResponse();

        response.setId(url.getId());
        response.setTimesClicked(url.getTimesClicked());
        response.setTimesShortened(url.getTimesShortened());
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
    public static MiniUrlResponse mapper(Url url, String code, String description){

        MiniUrlResponse response = new MiniUrlResponse();

        response.setId(url.getId());
        response.setTimesClicked(url.getTimesClicked());
        response.setTimesShortened(url.getTimesShortened());
        response.setCode(code);
        response.setDescription(description);

        return response;
    }

}
