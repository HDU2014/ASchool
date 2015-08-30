package com.hdu.tx.aschool.net;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hdu.tx.aschool.base.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by chenglin on 2015/8/29.
 */
public class MyStringRequest {
    private String action;
    private Map<String,String> map;
    private InternetListener listener;

    public MyStringRequest(String action,Map<String,String> map,InternetListener listener) {
        this.action=action;
        this.map=map;
        this.listener=listener;
        start();
    }

    public MyStringRequest(String action,InternetListener listener) {
        this.action=action;
        this.listener=listener;
        start();
    }

    private void start(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                action, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject object=new JSONObject(s);
                    if(object.getInt("result")==200){
                        listener.success(object);
                    }else{
                        listener.error(object.getString("desc"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.error(e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.error(volleyError.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        MyApplication.getInstance().getVolleyQueue().add(stringRequest);
    }

}
