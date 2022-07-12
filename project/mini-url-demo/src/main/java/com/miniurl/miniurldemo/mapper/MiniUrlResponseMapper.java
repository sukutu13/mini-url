package com.miniurl.miniurldemo.mapper;

import com.miniurl.miniurldemo.entity.Url;
import com.miniurl.miniurldemo.response.MiniUrlResponse;

public class MiniUrlResponseMapper {
    
    public static MiniUrlResponse mapper(Url url){

        MiniUrlResponse response = new MiniUrlResponse();

        response.setId(url.getId());
        response.setTimesClicked(url.getTimesClicked());
        response.setTimesShortened(url.getTimesShortened());
        response.setCode("00");
        response.setDescription("Sucesso");

        return response;
    }

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
