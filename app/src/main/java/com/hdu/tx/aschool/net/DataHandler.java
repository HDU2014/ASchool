package com.hdu.tx.aschool.net;

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
public class DataHandler {
    private static final String TAG ="MyStringRequest" ;
    private String action;
    private InternetListener listener;
    private String sql;



    private void start(final String sql,final InternetListener listener){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                "api.xtongtong.cn/api/activity_query_mult_avtivity_sql.php", new Response.Listener<String>() {
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

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("user_name",MyApplication.getInstance().getUserInfo().getUsername());
                map.put("sql",sql);
                return map;
            }
        };
        MyApplication.getInstance().getVolleyQueue().add(stringRequest);
    }


    public void getUnOpenDataByPage(InternetListener listener){
        String sql="select * from info_activity where open='false'";
        start(sql,listener);
    }


}
