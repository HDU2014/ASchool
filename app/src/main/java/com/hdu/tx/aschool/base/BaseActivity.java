package com.hdu.tx.aschool.base;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 2015/8/13.
 */
public class BaseActivity extends AppCompatActivity {
    public void toast(View view,String message){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
