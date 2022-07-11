package com.miniurl.miniurldemo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniurl.miniurldemo.entity.Url;
import com.miniurl.miniurldemo.repository.IUrlRepo;

@Service
public class RetrieverLongUrlService {

    @Autowired
    IUrlRepo urlRepo;

    /**
     * Get from the database the long url from ist's id
     * @param id - String - Url's id
     * @return The url information or null if do not exists
     * @throws Exception - Throws exception in case the get thows it as well
     */
    public Url findLongUrl(String id) throws Exception {
        Optional<Url> opUrl = urlRepo.findById(id);

        if(!opUrl.isPresent()){
            return null;
        }

        Url url = opUrl.get();

        url.setTimesClicked(url.getTimesClicked()+1);

        urlRepo.save(url);

        return url;
    }

}
