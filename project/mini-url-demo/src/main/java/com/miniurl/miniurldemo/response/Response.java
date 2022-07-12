package com.miniurl.miniurldemo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Response {
    
    private String code;
    private String description;
}
