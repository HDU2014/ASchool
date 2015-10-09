package com.hdu.tx.aschool.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.ui.Controller.ChatController;
import com.hdu.tx.aschool.ui.Controller.MainController;
import com.hdu.tx.aschool.ui.View.MainView;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;
import com.umeng.update.UmengUpdateAgent;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private MainView mainView;
    private MainController mainController;
    private ChatController chatController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.enable();

        UmengUpdateAgent.update(this);
        String device_token = UmengRegistrar.getRegistrationId(this);
        Log.i(TAG,device_token);
        mainView = (MainView) findViewById(R.id.dl_main_drawer);
        mainView.initView(this);
        if(getIntent().getBooleanExtra("isRefresh",false)){
            mainView.refreshMyInfo();
        }
        mainController = new MainController(this, mainView);
        chatController=new ChatController(this,savedInstanceState);
        mainController.setChatController(chatController);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mainController.onActivityResult(requestCode, resultCode, data);
    }
}
