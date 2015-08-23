package com.hdu.tx.aschool.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.common.view.EditActivity;
import com.hdu.tx.aschool.dao.DaoMaster;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.entity.UserInfoEntity;

public class SplashActivity extends BaseActivity
{
	private static final String SYSTEMCACHE = "SplashActivity";


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
		setContentView(R.layout.splash);
	}


	private static final int sleepTime = 2000;
	@Override
	protected void onStart() {
		super.onStart();
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(sleepTime);
					UserInfo userInfo=MyApplication.getInstance().getUserInfo();
					if(userInfo==null){
						userInfo=new UserInfo();
						userInfo.setLevel(0);
						userInfo.setId(1l);
						MyApplication.getInstance().getDaoSession().insert(userInfo);
					}
				} catch (InterruptedException e) {

				}
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
				finish();
			}
		}).start();
	}
}
