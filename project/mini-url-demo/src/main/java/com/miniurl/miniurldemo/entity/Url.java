package com.miniurl.miniurldemo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name="tbl_url")
@Entity
@Getter
@Setter
@ToString
public class Url {
    
    @Id
    private String id;

    @Column(name="big_url")
    private String bigUrl;

    @Column(name="times_shortened")
    private int timesShortened;

    @Column(name = "times_clicked")
    private int timesClicked;

    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name="update_at")
    private Date updatedAt;

}
