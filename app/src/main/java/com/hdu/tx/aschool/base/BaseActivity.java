package com.hdu.tx.aschool.base;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2015/8/13.
 */
public class BaseActivity extends AppCompatActivity {


    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public RequestQueue getVolleyQueue(){
        if (queue == null) {
           queue = Volley.newRequestQueue(getApplicationContext());
        }
        return queue;
    }

    public void toast(View view,String message){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

}
