package com.miniurl.miniurldemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniurl.miniurldemo.entity.Url;
import com.miniurl.miniurldemo.handler.GenerateMiniUrlHandler;
import com.miniurl.miniurldemo.repository.IUrlRepo;

@Service
public class GenerateMiniUrlService {

    @Autowired
    IUrlRepo urlRepo;
    @Autowired
    GenerateMiniUrlHandler handler;

    public Url saveLongUrl(Url url) throws Exception {
        int iniPos = 0;
        boolean same = true;
        Url url2 = urlRepo.findByBigUrl(url.getBigUrl());

        if (url2 != null) {
            url2.setTimesShortened(url2.getTimesShortened() + 1);
            return urlRepo.save(url2);
        }

        String bigId = handler.generateMiniUrl(url);
        String id = "";

        while (same && iniPos < 26) {
            id = bigId.substring(iniPos, iniPos + 7);
            same = urlRepo.findById(id).isPresent();
            if (same) {
                iniPos++;
            }
        }

        if(same){
            throw new Exception("Hash Collision") ;
        }

        url.setId(id);
        url.setTimesShortened(url.getTimesShortened() + 1);

        return urlRepo.save(url);
    }

}
