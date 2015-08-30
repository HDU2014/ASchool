package com.hdu.tx.aschool.net;

import org.json.JSONObject;

/**
 * Created by chenglin on 2015/8/29.
 */
public interface InternetListener {
    void success(JSONObject desc);
    void error(String desc);

}
