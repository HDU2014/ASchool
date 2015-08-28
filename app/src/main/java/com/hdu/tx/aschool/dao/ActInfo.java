package com.hdu.tx.aschool.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import java.io.Serializable;

/**
 * Entity mapped to table "ACT_INFO".
 */
public class ActInfo implements Serializable{

    private Long id;
    private String hostname;
    private String imageUrl;
    private String title;
    private String time;
    private String address;
    private String describe;
    private Integer hostId;
    private Integer lookTimes;
    private Integer collectTimes;
    private Integer totalpeopel;
    private Integer joinedpeopel;

    public ActInfo() {
    }

    public ActInfo(Long id) {
        this.id = id;
    }

    public ActInfo(Long id, String hostname, String imageUrl, String title, String time, String address, String describe, Integer hostId, Integer lookTimes, Integer collectTimes, Integer totalpeopel, Integer joinedpeopel) {
        this.id = id;
        this.hostname = hostname;
        this.imageUrl = imageUrl;
        this.title = title;
        this.time = time;
        this.address = address;
        this.describe = describe;
        this.hostId = hostId;
        this.lookTimes = lookTimes;
        this.collectTimes = collectTimes;
        this.totalpeopel = totalpeopel;
        this.joinedpeopel = joinedpeopel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public Integer getLookTimes() {
        return lookTimes;
    }

    public void setLookTimes(Integer lookTimes) {
        this.lookTimes = lookTimes;
    }

    public Integer getCollectTimes() {
        return collectTimes;
    }

    public void setCollectTimes(Integer collectTimes) {
        this.collectTimes = collectTimes;
    }

    public Integer getTotalpeopel() {
        return totalpeopel;
    }

    public void setTotalpeopel(Integer totalpeopel) {
        this.totalpeopel = totalpeopel;
    }

    public Integer getJoinedpeopel() {
        return joinedpeopel;
    }

    public void setJoinedpeopel(Integer joinedpeopel) {
        this.joinedpeopel = joinedpeopel;
    }

}
