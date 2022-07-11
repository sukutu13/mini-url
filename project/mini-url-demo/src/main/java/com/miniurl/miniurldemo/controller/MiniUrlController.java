package com.miniurl.miniurldemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.miniurl.miniurldemo.service.GenerateMiniUrlService;
import com.miniurl.miniurldemo.service.RetrieverLongUrlService;
import com.miniurl.miniurldemo.entity.Url;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MiniUrlController{

    @GetMapping(value="/")
    public String getMethodName(){
        return "Request is working";
    }

    @Autowired
    RetrieverLongUrlService retrieverLongUrl;

    @GetMapping(value="/{id}")
    public ResponseEntity<Url> getLongUrl(@PathVariable String id){
        try {
            Url url = retrieverLongUrl.findLongUrl(id);
            if(null != url){
                return new ResponseEntity<>(url, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    GenerateMiniUrlService generateMiniUrl;

    @PostMapping(value="/minify")
    public ResponseEntity<Url> saveLongUrl(@RequestBody Url url) {
        try {
            return new ResponseEntity<>(generateMiniUrl.saveLongUrl(url), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}