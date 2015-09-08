package com.hdu.tx.aschool.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2015/8/13.
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog dialog;
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

    public void showProgressDialog(Activity activity,String message){
       if(dialog==null)dialog=new ProgressDialog(activity);
        dialog.setMessage(message);
        dialog.show();
    }

    public void showProgressDialog(Activity activity,int id){
        if(dialog==null)dialog=new ProgressDialog(activity);
        dialog.setMessage(getApplicationContext().getResources().getString(id));
        dialog.show();
    }

    public void dismissProgressDialog(){
        if(dialog!=null&&dialog.isShowing())dialog.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
