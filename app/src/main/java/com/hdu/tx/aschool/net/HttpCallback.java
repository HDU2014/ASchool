package com.hdu.tx.aschool.net;

import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.dao.UserInfo;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by chenglin on 2015/8/30.
 */
public class HttpCallback implements InternetListener {
    public Map<String,String> map;


    @Override
    public void success(JSONObject json) {

    }

    @Override
    public void error(String desc) {

    }

    @Override
    public Map<String, String> setParams() {
        return null;
    }


}
