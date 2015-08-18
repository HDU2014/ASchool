package com.hdu.tx.aschool.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;

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
				} catch (InterruptedException e) {

				}
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
				finish();
			}
		}).start();
	}
}
