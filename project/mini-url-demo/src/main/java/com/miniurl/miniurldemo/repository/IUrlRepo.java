package com.miniurl.miniurldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniurl.miniurldemo.entity.Url;

public interface IUrlRepo extends JpaRepository<Url, String> {
    
    Url findByBigUrl(String bigUrl);

}
