package com.hdu.tx.aschool.ui.activity;

import android.os.Bundle;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.ui.Controller.MainController;
import com.hdu.tx.aschool.ui.View.MainView;

public class MainActivity extends BaseActivity {
    private MainView mainView;
    private MainController mainController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainView = (MainView) findViewById(R.id.dl_main_drawer);
        mainView.initView(this);
        mainController = new MainController(this, mainView);
    }



}
