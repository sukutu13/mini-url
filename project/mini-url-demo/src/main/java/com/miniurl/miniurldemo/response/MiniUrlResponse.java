package com.miniurl.miniurldemo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MiniUrlResponse extends Response{
    
    private String id;
    private int timesShortened;
    private int timesClicked;
}
