package com.hdu.tx.aschool.ui.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.easemod.applib.DemoHXSDKHelper;
import com.hdu.tx.aschool.ui.View.MainView;
import com.hdu.tx.aschool.ui.fragment.TypeFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

public class SplashActivity extends BaseActivity
{

	private static final String SYSTEMCACHE = "SplashActivity";
	@Bind(R.id.sp_tv)
	TextView spTv;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
		setContentView(R.layout.splash);
		ButterKnife.bind(this);
		PackageManager manager=this.getPackageManager();
		PackageInfo info = null;
		try {
			info = manager.getPackageInfo(this.getPackageName(), 0);
			spTv.setText(getResources().getString(R.string.app_name)+"V"+info.versionName);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
	}



	private static final int sleepTime = 2000;
	@Override
	protected void onStart() {
		super.onStart();
		new Thread(new Runnable() {
			public void run() {
					if (DemoHXSDKHelper.getInstance().isLogined()) {
						// ** 免登陆情况 加载所有本地群和会话
						//不是必须的，不加sdk也会自动异步去加载(不会重复加载)；
						//加上的话保证进了主页面会话和群组都已经load完毕
						long start = System.currentTimeMillis();
						EMGroupManager.getInstance().loadAllGroups();
						EMChatManager.getInstance().loadAllConversations();

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

						long costTime = System.currentTimeMillis() - start;
						//等待sleeptime时长
						if (sleepTime - costTime > 0) {
							try {
								Thread.sleep(sleepTime - costTime);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						//进入主页面
						startActivity(new Intent(SplashActivity.this, MainActivity.class));
						finish();
					}else {
						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {

						}
						startActivity(new Intent(SplashActivity.this, MainActivity.class));
						finish();
					}
				}
			}).start();
	}
}
