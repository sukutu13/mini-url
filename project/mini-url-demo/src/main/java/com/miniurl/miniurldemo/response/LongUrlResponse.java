package com.miniurl.miniurldemo.response;

import lombok.Getter;
import lombok.Setter;

//Response with big url
@Getter
@Setter
public class LongUrlResponse extends Response{
    
    private String bigUrl;
}
