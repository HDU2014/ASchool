package com.hdu.tx.aschool.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.hoang8f.widget.FButton;

/**
 * Created by Administrator on 2015/8/15.
 */
public class TestActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.iv_username)
    ImageView ivUsername;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.til_phone)
    TextInputLayout tilPhone;
    @Bind(R.id.bt_getYZM)
    FButton btGetYZM;
    @Bind(R.id.rlusername)
    RelativeLayout rlusername;
    @Bind(R.id.iv_password)
    ImageView ivPassword;
    @Bind(R.id.et_YZM)
    EditText etYZM;
    @Bind(R.id.til_yzm)
    TextInputLayout tilYzm;
    @Bind(R.id.rlpassword)
    RelativeLayout rlpassword;
    @Bind(R.id.iv_password1)
    ImageView ivPassword1;
    @Bind(R.id.et_pass)
    EditText etPass;
    @Bind(R.id.til_pass)
    TextInputLayout tilPass;
    @Bind(R.id.rlpassword1)
    RelativeLayout rlpassword1;
    @Bind(R.id.bt_regist)
    ActionProcessButton btRegist;
    @Bind(R.id.tv_unreceive_identify)
    TextView tvUnreceiveIdentify;
    @Bind(R.id.ll1)
    LinearLayout ll1;
    @Bind(R.id.iv_qq)
    ImageView ivQq;
    @Bind(R.id.iv_weixin)
    ImageView ivWeixin;
    @Bind(R.id.iv_weibo)
    ImageView ivWeibo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registactivity);
        ButterKnife.bind(this);
    }
}
