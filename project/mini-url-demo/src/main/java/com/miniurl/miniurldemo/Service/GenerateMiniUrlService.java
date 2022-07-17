package com.miniurl.miniurldemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniurl.miniurldemo.constant.Constants;
import com.miniurl.miniurldemo.entity.Url;
import com.miniurl.miniurldemo.mapper.MiniUrlResponseMapper;
import com.miniurl.miniurldemo.repository.IUrlRepo;
import com.miniurl.miniurldemo.request.MiniUrlRequest;
import com.miniurl.miniurldemo.response.MiniUrlResponse;
import com.miniurl.miniurldemo.util.MD5Util;

@Service
public class GenerateMiniUrlService {

    @Autowired
    IUrlRepo urlRepo;
    @Autowired
    MD5Util util;

    /**
     * Method that generates the mini url, creating a db regist of him if is new or
     * returning existing shortenings
     * 
     * @param url - Url - Object with the long url information
     * @return - The mini url corresponded to the long url
     * @throws Exception - Returns exception in case of id coligion
     */
    public MiniUrlResponse saveLongUrl(MiniUrlRequest request) throws Exception {
        int iniPos = 0;
        boolean same = true;

        if(null == request.getBigUrl()){
            return MiniUrlResponseMapper.mapper(new Url(), Constants.CODE_INVALID_BODY, Constants.DESCRIPTION_INVALID_BODY);
        }

        Url url = new Url();

        url.setBigUrl(request.getBigUrl());

        int index = url.getBigUrl().indexOf('.');
        if (-1 == index) {
            return MiniUrlResponseMapper.mapper(url, Constants.CODE_INVALID_URL, Constants.DESCRIPTION_INVALID_URL);
        }

        /* Seeing if the url was already shortened */
        Url url2 = urlRepo.findByBigUrl(url.getBigUrl());
        if (url2 != null) {
            url2.setTimesShortened(url2.getTimesShortened() + 1);
            urlRepo.save(url2);
            return MiniUrlResponseMapper.mapper(url2);
        }

        // Getting the hash code for the mini url
        String bigId = util.generateMiniUrl(url);
        String id = "";

        // Seeing if the generated hash code created any collisions
        while (same && iniPos < 26) {
            // Creating a length 7 id from the 32 length hash code
            id = bigId.substring(iniPos, iniPos + 7);
            same = urlRepo.findById(id).isPresent();
            if (same) {
                // moving one position to the right in order to obtain a diferent id
                iniPos++;
            }
        }

        // In case all the combination created a collision throw exception
        if (same) {
            return MiniUrlResponseMapper.mapper(new Url(), Constants.CODE_HASH_COLLISION, Constants.DESCRIPTION_HASH_COLLISION);
        }

        url.setId(id);
        // Adding one to the shortened counter of the url
        url.setTimesShortened(url.getTimesShortened() + 1);

        urlRepo.save(url);

        return MiniUrlResponseMapper.mapper(url);
    }

}
