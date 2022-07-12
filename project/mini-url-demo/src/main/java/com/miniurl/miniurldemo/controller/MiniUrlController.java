package com.miniurl.miniurldemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.miniurl.miniurldemo.service.GenerateMiniUrlService;
import com.miniurl.miniurldemo.service.RetrieverLongUrlService;
import com.miniurl.miniurldemo.request.MiniUrlRequest;
import com.miniurl.miniurldemo.response.Response;

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
    public ResponseEntity<Response> getLongUrl(@PathVariable String id){
        try {
            Response response = retrieverLongUrl.findLongUrl(id);
            switch(response.getCode()){
                case "00":
                return new ResponseEntity<>(response, HttpStatus.OK);
                case "01":
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
                default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
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
    public ResponseEntity<Response> saveLongUrl(@RequestBody MiniUrlRequest url) {
        try {
            Response response = generateMiniUrl.saveLongUrl(url);
            switch(response.getCode()){
                case "00":
                return new ResponseEntity<>(response, HttpStatus.OK);
                case "02":
                return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
                case "03":
                return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
                default:
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}