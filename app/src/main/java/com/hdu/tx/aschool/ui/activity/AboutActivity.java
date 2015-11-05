package com.hdu.tx.aschool.ui.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by pualgo on 2015/9/3.
 */
public class AboutActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.main_content)
    LinearLayout mainContent;
    @Bind(R.id.version_tv)
    TextView versionTv;
    @Bind(R.id.version_message_tv)
    TextView versionMsgTv;

    private boolean haveNewestVersion;

    @OnClick(R.id.version_message_tv)void onclick(){
        if(haveNewestVersion)UmengUpdateAgent.forceUpdate(this);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_soft);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        checkUpdata();
    }

    private void checkUpdata() {
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int updateStatus,UpdateResponse updateInfo) {
                switch (updateStatus) {
                    case UpdateStatus.Yes: // has update
                        haveNewestVersion=true;
                        versionMsgTv.setTextColor(getResources().getColor(R.color.red));
                        versionMsgTv.setText("发现最新版本"+updateInfo.version);
                        break;
                    case UpdateStatus.No: // has no update
                        versionMsgTv.setText("当前已是最新版本");
                        break;
                    case UpdateStatus.NoneWifi: // none wifi
                        toast(toolbar,"没有wifi连接， 只在wifi下更新");
                        break;
                    case UpdateStatus.Timeout: // time out
                        toast(toolbar,"连接超时");
                        break;
                }
            }
        });
        UmengUpdateAgent.update(this);
    }


    public void init(){
        PackageManager manager=this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
            versionTv.setText("版本号:"+info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
