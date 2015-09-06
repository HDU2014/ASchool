package com.hdu.tx.aschool.ui.activity;

import android.os.Bundle;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.ui.Controller.ChatController;
import com.hdu.tx.aschool.ui.Controller.MainController;
import com.hdu.tx.aschool.ui.View.MainView;

public class MainActivity extends BaseActivity {
    private MainView mainView;
    private MainController mainController;
    private ChatController chatController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainView = (MainView) findViewById(R.id.dl_main_drawer);
        mainView.initView(this);
        if(getIntent().getBooleanExtra("isRefresh",false)){
            mainView.refreshMyInfo();
        }
        mainController = new MainController(this, mainView);

        chatController=new ChatController(this,savedInstanceState);


    }

}
