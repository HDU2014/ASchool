package com.hdu.tx.aschool.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dd.processbutton.iml.ActionProcessButton;
import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.common.utils.MySecurity;
import com.hdu.tx.aschool.common.utils.ThirdKey;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.easemod.utils.CommonUtils;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.JSONHandler;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.net.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import info.hoang8f.widget.FButton;

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
    FButton btGetYZM;
    @Bind(R.id.rlpassword)
    RelativeLayout rlpassword;
    @OnClick(R.id.tv_unreceive_identify)void onclick3(){
        if(isCompleteInput[0])getVoiceVerificationCode();
        else this.toast(toolbar,"手机号码不正确");
    }

    @OnClick(R.id.bt_regist)
    void click2() {
        if(isCompleteInput[0]&&isCompleteInput[1]&&isCompleteInput[2]){
            btRegist.setProgress(20);
            //submit(etPhone.getText().toString().trim(), etYZM.getText().toString().trim());
            registFromServer();
        }else{
            Snackbar.make(toolbar,R.string.input_format_error,Snackbar.LENGTH_SHORT).show();
        }

    }


    @Bind(R.id.til_phone)
    TextInputLayout tilPhone;
    @Bind(R.id.til_yzm)
    TextInputLayout tilYzm;
    @Bind(R.id.til_pass)
    TextInputLayout tilPass;

    @OnClick(R.id.bt_getYZM)
    void click1() {
        getYanZhengMa(etPhone.getText().toString().trim());
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
    ActionProcessButton btRegist;
    @Bind(R.id.iv_qq)
    ImageView ivQq;
    @Bind(R.id.iv_weixin)
    ImageView ivWeixin;
    @Bind(R.id.iv_weibo)
    ImageView ivWeibo;

    private final static int PHONENUMEBER_LENGTH = 11;
    private boolean[] isCompleteInput = new boolean[3];
    private EventHandler eventHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registactivity);
        ButterKnife.bind(this);
        initToolbar();
        initSMSSDK();
        startTextWatch();
    }

    /**
     * 监听输入内容
     */
    private void startTextWatch() {
        tilPass.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isCompleteInput[2] = (s.length() > 0) ? true : false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                btRegist.setEnabled(isCompleteInput[0] && isCompleteInput[1] && isCompleteInput[2]);
            }
        });

        tilPhone.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                isCompleteInput[0] = (s.length() == PHONENUMEBER_LENGTH) ? true : false;
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
                isCompleteInput[1] = (s.length() > 0) ? true : false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                btRegist.setEnabled(isCompleteInput[0] && isCompleteInput[1] && isCompleteInput[2]);
            }
        });
    }

    /**
     * 短信验证码
     */
    private void initSMSSDK() {
        SMSSDK.initSDK(this, ThirdKey.APPKEY, ThirdKey.APPSECRET);
        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(final int event, final int result,
                                   final Object data) {
                try {

                    if (result == SMSSDK.RESULT_COMPLETE) {
                        //回调完成
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                            registFromServer();
                        } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                            //Toast.makeText(RegistActivity.this,"获取验证码成功。。。",Toast.LENGTH_LONG).show();
                            RegistActivity.this.toast(toolbar, "开始获取验证码");
                        }else if(event == SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE){
                            RegistActivity.this.toast(toolbar, "开始获取语音验证码");
                        }
                    } else {
                        ((Throwable) data).printStackTrace();
                        btRegist.setProgress(-1);
                        Snackbar.make(toolbar,R.string.getyzm_error,Snackbar.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    Snackbar.make(toolbar,e.toString(),Snackbar.LENGTH_LONG).show();
                    btRegist.setProgress(-1);
                }


            }
        };
        SMSSDK.registerEventHandler(eventHandler);
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


    /**
     * 倒计时处理
     */
    Timer timer;
    int leftTime = 60;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            btGetYZM.setText("请等待" + (leftTime--) + "秒");
            if (leftTime == -2) {
                timer.cancel();
                btGetYZM.setText("重新获取");
                btGetYZM.setEnabled(true);
            }
        }
    };

    /**
     * 获取验证码
     *
     * @param phone 手机号码
     */
    public void getYanZhengMa(String phone) {
        String phoneNumber = etPhone.getText().toString().trim();
        if (!phoneNumber.matches("[1][3-8][\\d]{9}")) {
            toast(toolbar, "请输入正确的手机号");
            return;
        }
        leftTime = 60;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 0, 1000);
        btGetYZM.setEnabled(false);
        SMSSDK.getVerificationCode("86", phone);
    }


    private void submit(String phone, String yzm) {
        SMSSDK.submitVerificationCode("86", phone, yzm);
    }

    /**
     * 提示使用语音验证码
     */
    public void getVoiceVerificationCode() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("语音验证码").setMessage(R.string.smssdk_send_sounds_identify_code)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SMSSDK.getVoiceVerifyCode(etPhone.getText().toString().trim(), "86");
                    }
                }).setNegativeButton("取消", null).create().show();
    }


    public void registFromServer(){
        new MyStringRequest(Urls.USER_REGIST, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                UserInfo userInfo= JSONHandler.json2UserInfo(json);
                userInfo.setLevel(1);
                userInfo.setId(1l);
                userInfo.setLoadTimes(1);
                MyApplication.getInstance().getDaoSession().deleteAll(UserInfo.class);
                MyApplication.getInstance().getDaoSession().insert(userInfo);
                MyApplication.getInstance().setUserInfo(userInfo);
               loginEMChat(etPhone.getText().toString(),new MySecurity().encodyByMD5(etPass.getText().toString()));
            }

            @Override
            public void error(String desc) {
                btRegist.setProgress(-1);
                Snackbar.make(btRegist,desc,Snackbar.LENGTH_LONG).show();
            }

            @Override
            public Map<String, String> setParams() {
                Map<String,String> map=new HashMap<>();
                map.put("user_name",etPhone.getText().toString());
                String pwd_md5 = new MySecurity().encodyByMD5(etPass.getText().toString());
                map.put("password",pwd_md5);
                return map;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    public void loginEMChat(final String user, final String pass){
        if (!CommonUtils.isNetWorkConnected(this)) {
            Snackbar.make(toolbar, R.string.network_isnot_available, Snackbar.LENGTH_SHORT).show();
            return;
        }

        EMChatManager.getInstance().login(user, pass, new EMCallBack() {
            @Override
            public void onSuccess() {
                MyApplication.getInstance().login(user, pass);
                Intent intent=new Intent(RegistActivity.this,MyInfoActivity.class);
                RegistActivity.this.startActivity(intent);
                RegistActivity.this.finish();
            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
