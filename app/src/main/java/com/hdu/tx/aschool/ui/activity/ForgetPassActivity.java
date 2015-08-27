package com.hdu.tx.aschool.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
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
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dd.processbutton.iml.ActionProcessButton;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.common.utils.MySecurity;
import com.hdu.tx.aschool.common.utils.ThirdKey;
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
public class ForgetPassActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.username_iv)
    ImageView usernameIv;
    @Bind(R.id.phone_tv)
    EditText phoneTv;
    @Bind(R.id.phone_til)
    TextInputLayout phoneTil;
    @Bind(R.id.yzm_bt)
    FButton yzmBt;
    @OnClick(R.id.yzm_bt)void onclick(){
        getYanZhengMa(phoneTv.getText().toString().trim());
    }
    @Bind(R.id.rl1)
    RelativeLayout rl1;
    @Bind(R.id.yzm_iv)
    ImageView yzmIv;
    @Bind(R.id.yzm_et)
    EditText yzmEt;
    @Bind(R.id.rl2)
    RelativeLayout rl2;
    @Bind(R.id.pass_iv)
    ImageView passIv;
    @Bind(R.id.pass_et)
    EditText passEt;
    @Bind(R.id.pass_til)
    TextInputLayout passTil;
    @Bind(R.id.rl3)
    RelativeLayout rl3;
    @Bind(R.id.bt_find)
    ActionProcessButton btFind;
    @Bind(R.id.tv_unreceive_identify)
    TextView tvUnreceiveIdentify;
    @OnClick(R.id.tv_unreceive_identify)void onclick2(){
        getVoiceVerificationCode();
    }
    @Bind(R.id.yzm_til)
    TextInputLayout yzmTil;
    private boolean[] isCompleteInput = new boolean[3];
    private EventHandler eventHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.findpassword);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initSMSSDK();
        startTextWatch();


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
                            requestServer();
                        } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                            //Toast.makeText(RegistActivity.this,"获取验证码成功。。。",Toast.LENGTH_LONG).show();
                            ForgetPassActivity.this.toast(toolbar, "开始获取验证码");
                        } else if (event == SMSSDK.EVENT_GET_VOICE_VERIFICATION_CODE) {
                            ForgetPassActivity.this.toast(toolbar, "开始获取语音验证码");
                        } else if(event==SMSSDK.RESULT_ERROR){
                            ForgetPassActivity.this.toast(toolbar, "验证码错误");
                        }
                    } else {
                        ((Throwable) data).printStackTrace();
                        btFind.setProgress(-1);

                    }

                } catch (Exception e) {
                    Snackbar.make(toolbar, e.toString(), Snackbar.LENGTH_SHORT).show();
                }


            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }

    /**
     * 向服务器请求找回密码
     */
    private void requestServer() {
        try {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.USER_UPDATE_PASSWORD, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    try {
                        JSONObject object = new JSONObject(s);
                        int code = object.getInt("result");
                        if (code == 200) {
                            btFind.setProgress(100);

                        } else {
                            btFind.setProgress(-1);
                        }
                    } catch (JSONException e) {

                        btFind.setProgress(-1);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    btFind.setProgress(-1);
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("user_name", passEt.getText().toString());
                    String pwd_md5 = new MySecurity().encodyByMD5(passEt.getText().toString());
                    map.put("password", pwd_md5);
                    return map;
                }
            };
            getVolleyQueue().add(stringRequest);

        } catch (Exception e) {
            toast(toolbar, e.toString());
        }
    }


    /**
     * 监听输入内容
     */
    private void startTextWatch() {
        passTil.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isCompleteInput[2] = (s.length() > 0) ? true : false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                btFind.setEnabled(isCompleteInput[0] && isCompleteInput[1] && isCompleteInput[2]);
            }
        });

        phoneTil.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                isCompleteInput[0] = (s.length() == 11) ? true : false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                yzmBt.setEnabled(isCompleteInput[0]);
                btFind.setEnabled(isCompleteInput[0] && isCompleteInput[1] && isCompleteInput[2]);
            }
        });

        yzmTil.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isCompleteInput[1] = (s.length() > 0) ? true : false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                btFind.setEnabled(isCompleteInput[0] && isCompleteInput[1] && isCompleteInput[2]);
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
            yzmBt.setText("请等待" + (leftTime--) + "秒");
            if (leftTime == -2) {
                timer.cancel();
                yzmBt.setText("重新获取");
                yzmBt.setEnabled(true);
            }
        }
    };

    /**
     * 获取验证码
     *
     * @param phone 手机号码
     */
    public void getYanZhengMa(String phone) {
        String phoneNumber = phoneTv.getText().toString().trim();
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
        yzmBt.setEnabled(false);
        SMSSDK.getVerificationCode("86", phone);
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
                        SMSSDK.getVoiceVerifyCode(passEt.getText().toString().trim(), "86");
                    }
                }).setNegativeButton("取消", null).create().show();
    }

    /**
     * 点击找回密码按钮
     */
    @OnClick(R.id.bt_find)
    void findPassword() {
        SMSSDK.submitVerificationCode("86", phoneTv.getText().toString(), yzmEt.getText().toString());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
