package com.hdu.tx.aschool.net;

import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.dao.UserInfo;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenglin on 2015/8/29.
 */
public interface InternetListener {
    void success(JSONObject json);
    void error(String desc);
    Map<String,String> setParams();
}
