package com.miniurl.miniurldemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniurl.miniurldemo.Service.GenerateMiniUrlService;
import com.miniurl.miniurldemo.entity.Url;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MiniUrlController{

    @GetMapping(value="/")
    public String getMethodName(){
        return "Request is working";
    }

    @GetMapping(value="/{id}")
    public String getLongUrl(){
        //TODO: get and return the long url stored on the db and add 1 to the clicks
        return "";
    }

    @Autowired
    GenerateMiniUrlService handler;

    @PostMapping(value="/minify")
    public ResponseEntity<Url> saveLongUrl(@RequestBody Url url) {
        try {
            return new ResponseEntity<>(handler.saveLongUrl(url), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}