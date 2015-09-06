package com.hdu.tx.aschool.net;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hdu.tx.aschool.base.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenglin on 2015/8/29.
 */
public class MyStringRequest {
    private static final String TAG ="MyStringRequest" ;
    private String action;
    private InternetListener listener;

    public MyStringRequest(String action,InternetListener listener) {
        this.action=Urls.API_URL+action+Urls.PHP;
        this.listener=listener;
        start();
    }

    private void start(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
               action , new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d(TAG, "解析" + action + "请求返回的JSON：" + s);
                try {
                    JSONObject object=new JSONObject(s);
                    if(object.getInt("result")==200){
                        listener.success(object);
                    }else{
                        Log.d(TAG,"请求"+action+"地址失败:"+object.getString("desc"));
                        listener.error(object.getString("desc"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "解析" + action + "请求返回的JSON错误：" + e.toString());
                    listener.error(e.toString());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "请求"+action+"地址错误："+volleyError.toString());
                listener.error(volleyError.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=listener.setParams()==null?new HashMap<String,String>():listener.setParams();
                if(MyApplication.getInstance().getUserInfo()!=null&& !map.containsKey("user_name"))
                    map.put("user_name",MyApplication.getInstance().getUserInfo().getUsername());
                return map;
            }
        };
        MyApplication.getInstance().getVolleyQueue().add(stringRequest);
    }

}
