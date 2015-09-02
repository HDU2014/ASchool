package com.hdu.tx.aschool.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.ui.View.MainView;
import com.hdu.tx.aschool.ui.fragment.TypeFragment;

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
						userInfo.setLevel(0);  //设置游客登录
						userInfo.setId(1l);
						userInfo.setLoadTimes(1);
						userInfo.setUsername("youke");
						MyApplication.getInstance().getDaoSession().insert(userInfo);
					}else{
						int times;
						if(userInfo.getLoadTimes()==null)times=0;
						else times=userInfo.getLoadTimes();
						userInfo.setLoadTimes(times+1);
						MyApplication.getInstance().getDaoSession().update(userInfo);
					}
				} catch (InterruptedException e) {

				}
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
				finish();
			}
		}).start();
	}
}
