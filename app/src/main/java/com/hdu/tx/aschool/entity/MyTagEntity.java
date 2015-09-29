package com.hdu.tx.aschool.entity;

import com.hdu.tx.aschool.dao.ActInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenglin on 2015/8/31.
 */
public class MyTagEntity implements Serializable{
    private String type;
    private List<ActInfo> actInfos;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ActInfo> getActInfos() {
        return actInfos;
    }

    public void setActInfos(List<ActInfo> actInfos) {
        this.actInfos = actInfos;
    }
}
