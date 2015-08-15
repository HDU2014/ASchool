package com.hdu.tx.aschool.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/8/12.
 */
public class RegistActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_username)
    ImageView ivUsername;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.bt_getYZM)
    Button btGetYZM;
    @Bind(R.id.til_phone)
    TextInputLayout tilPhone;
    @Bind(R.id.til_yzm)
    TextInputLayout tilYzm;
    @Bind(R.id.til_pass)
    TextInputLayout tilPass;

    @OnClick(R.id.bt_getYZM)
    void click1() {
        getYanZhengMa();
    }

    @Bind(R.id.iv_password)
    ImageView ivPassword;
    @Bind(R.id.et_YZM)
    EditText etYZM;
    @Bind(R.id.iv_password1)
    ImageView ivPassword1;
    @Bind(R.id.et_pass)
    EditText etPass;
    @Bind(R.id.bt_regist)
    Button btRegist;
    @Bind(R.id.iv_qq)
    ImageView ivQq;
    @Bind(R.id.iv_weixin)
    ImageView ivWeixin;
    @Bind(R.id.iv_weibo)
    ImageView ivWeibo;

    private final static int PHONENUMEBER_LENGTH = 11;
    private boolean[] isCompleteInput = new boolean[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registactivity);
        ButterKnife.bind(this);
        initToolbar();


        tilPass.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isCompleteInput[2] = (count + start > 0) ? true : false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                btGetYZM.setEnabled(isCompleteInput[0]);
                btRegist.setEnabled(isCompleteInput[0] && isCompleteInput[1] && isCompleteInput[2]);
            }
        });

        tilPhone.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                isCompleteInput[0] = (count + start == PHONENUMEBER_LENGTH) ? true : false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                btGetYZM.setEnabled(isCompleteInput[0]);
                btRegist.setEnabled(isCompleteInput[0] && isCompleteInput[1] && isCompleteInput[2]);
            }
        });

        tilYzm.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isCompleteInput[1] = (count + start == 6) ? true : false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                btGetYZM.setEnabled(isCompleteInput[0]);
                btRegist.setEnabled(isCompleteInput[0] && isCompleteInput[1] && isCompleteInput[2]);
            }
        });

    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.regist);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    Timer timer;
    int leftTime = 10;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            btGetYZM.setText("请等待" + (leftTime--) + "秒");
            if (leftTime == -2) {
                timer.cancel();
                btGetYZM.setText("重新获取");
                btGetYZM.setEnabled(true);
            } else {
                btGetYZM.setEnabled(false);
            }
        }
    };

    public void getYanZhengMa() {


        String phoneNumber = etPhone.getText().toString().trim();
        if (!phoneNumber.matches("[1][3-8][\\d]{9}")) {
            toast(toolbar,"请输入正确的手机号");
            return;
        }

        leftTime = 10;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 0, 1000);
        toast(toolbar,"get YZM");
        btGetYZM.setEnabled(false);
        isCompleteInput[0] = false;
    }


}
