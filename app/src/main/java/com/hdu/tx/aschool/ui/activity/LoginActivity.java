package com.hdu.tx.aschool.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.common.utils.MyStrings;
import com.hdu.tx.aschool.ui.IEvent.IEventView;

import java.util.HashMap;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;


/**
 * Created by Administrator on 2015/8/12.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, IEventView,Handler.Callback {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_username)
    ImageView ivUsername;
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.iv_password)
    ImageView ivPassword;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.tv_forgetpass)
    TextView tvForgetpass;
    @Bind(R.id.tv_regist)
    TextView tvRegist;

    public boolean isCompleteUsername;
    public boolean isCompletePassword;
    @Bind(R.id.et_password)
    EditText etPassword;


    private final static int PHONENUMEBER_LENGTH = 11;
    @Bind(R.id.textinput)
    TextInputLayout textinput;
    @Bind(R.id.til_pass)
    TextInputLayout tilPass;

    private boolean isRightPhoneNumber;
    private boolean isRightPassword;
    private boolean ready;

    // 填写从短信SDK应用后台注册得到的APPKEY
    private static String APPKEY = "9a31ea40a5e8";
    // 填写从短信SDK应用后台注册得到的APPSECRET
    private static String APPSECRET = "f7ca2df6bf72d5600965b7610b17f322";

    @OnClick({R.id.iv_qq, R.id.iv_weixin, R.id.iv_weibo})
    void select(ImageView imageView) {
        selectThirdAccount(imageView);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        initToolbar();
        tvRegist.setOnClickListener(this);
        initSDK();

        EditText editText = textinput.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 11) {
                    textinput.setErrorEnabled(true);
                    textinput.setError("手机号长度不正确");
                    isRightPhoneNumber = false;
                } else {
                    isRightPhoneNumber = true;
                    textinput.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isRightPassword && isRightPhoneNumber) {
                    btLogin.setEnabled(true);
                } else {
                    btLogin.setEnabled(false);
                }
            }
        });


        EditText passet = tilPass.getEditText();
        passet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 6) {
                    tilPass.setErrorEnabled(true);
                    tilPass.setError("密码强度弱");
                    isRightPassword = false;
                } else {
                    isRightPassword = true;
                    tilPass.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isRightPassword && isRightPhoneNumber) {
                    btLogin.setEnabled(true);
                } else {
                    btLogin.setEnabled(false);
                }
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
        toolbar.setTitle(R.string.login);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_regist:
//                Intent intent = new Intent(this, RegistActivity.class);
//                startActivity(intent);
                showRegisterPag();
                break;
        }
    }

    public TextWatcher usernameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count + start == PHONENUMEBER_LENGTH) {
                isCompleteUsername = true;
            } else {
                isCompleteUsername = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (isCompleteUsername && isCompletePassword) {
                btLogin.setEnabled(true);
            } else {
                btLogin.setEnabled(false);
            }
        }
    };

    public TextWatcher passwordextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count + start > 0) {
                isCompletePassword = true;
            } else {
                isCompleteUsername = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (isCompleteUsername && isCompleteUsername) {
                btLogin.setEnabled(true);
            } else {
                btLogin.setEnabled(false);
            }
        }
    };

    @OnClick(R.id.bt_login)
    void login() {
        toast(toolbar, "login");
    }

    @OnClick(R.id.tv_forgetpass)
    void findPass() {
        toast(toolbar, "findPass");
        Intent intent = new Intent(this, ForgetPassActivity.class);
        startActivity(intent);
    }


    @Override
    public void selectThirdAccount(ImageView imageView) {
        switch (imageView.getId()) {
            case R.id.iv_qq:
                toast(toolbar, "qq login");
                break;
            case R.id.iv_weibo:
                toast(toolbar, "weibo login");
                break;
            case R.id.iv_weixin:
                toast(toolbar, "weixin login");
                break;
        }
    }

    private void initSDK() {
        // 初始化短信SDK
        SMSSDK.initSDK(this, APPKEY, APPSECRET);
        final Handler handler = new Handler(this);
        EventHandler eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
        ready = true;

        // 获取新好友个数

        SMSSDK.getNewFriendsCount();

    }

    @Override
    public boolean handleMessage(Message msg) {
        int event = msg.arg1;
        int result = msg.arg2;
        Object data = msg.obj;
        if (event == SMSSDK.EVENT_SUBMIT_USER_INFO) {
            // 短信注册成功后，返回MainActivity,然后提示新好友
            if (result == SMSSDK.RESULT_COMPLETE) {
                Toast.makeText(this, R.string.smssdk_user_info_submited, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
        return false;
    }


    // 提交用户信息
    private void registerUser(String country, String phone) {
        Random rnd = new Random();
        int id = Math.abs(rnd.nextInt());
        String uid = String.valueOf(id);
        String nickName = "SmsSDK_User_" + uid;
        String avatar = MyStrings.AVATARS[id % 12];
        SMSSDK.submitUserInfo(uid, nickName, avatar, country, phone);
    }

    public void showRegisterPag(){
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");
                    // 提交用户信息
                    registerUser(country, phone);
                }
            }
        });
        registerPage.show(this);
    }

    @Override
    protected void onDestroy() {
        if (ready) {
            // 销毁回调监听接口
            SMSSDK.unregisterAllEventHandler();
        }
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ready) {
            // 获取新好友个数
            SMSSDK.getNewFriendsCount();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
