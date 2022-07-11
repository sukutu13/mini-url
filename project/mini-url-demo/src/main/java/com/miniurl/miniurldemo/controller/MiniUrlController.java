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

    @Autowired
    RetrieverLongUrlService retrieverLongUrl;

    /**
     * GET controller that retrieves the long url from his id
     * @param id - String - Path variable with the url id
     * @return - ResponseEntity with the url information if the url id exists in the DataBase
     */
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

    /**
     * POST controller that generates a mini url
     * @param url - Request body - With the long url to be shortened
     * @return - ResponseEntity with the url information including the, generated, mini url
     */
    @PostMapping(value="/minify")
    public ResponseEntity<Url> saveLongUrl(@RequestBody Url url) {
        try {
            int index = url.getBigUrl().indexOf('.');
            if(-1 == index){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            return new ResponseEntity<>(generateMiniUrl.saveLongUrl(url), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}