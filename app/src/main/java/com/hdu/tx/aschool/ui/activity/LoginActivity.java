package com.hdu.tx.aschool.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dd.processbutton.iml.ActionProcessButton;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.common.utils.MySecurity;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.net.Urls;
import com.hdu.tx.aschool.ui.IEvent.IEventView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2015/8/12.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, IEventView{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_username)
    ImageView ivUsername;
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.iv_password)
    ImageView ivPassword;
    @Bind(R.id.bt_login)
    ActionProcessButton btLogin;

    @Bind(R.id.tv_forgetpass)
    TextView tvForgetpass;

    @Bind(R.id.tv_regist)
    TextView tvRegist;

    public boolean isCompleteUsername;
    public boolean isCompletePassword;
    @Bind(R.id.et_password)
    EditText etPassword;

    @Bind(R.id.textinput)
    TextInputLayout textinput;
    @Bind(R.id.til_pass)
    TextInputLayout tilPass;

    private boolean isRightPhoneNumber;
    private boolean isRightPassword;
    private StringRequest stringRequest;

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
        editTextWatch();
    }

    private void editTextWatch() {
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
                if (s.length() < 6) {
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
                Intent intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
        }
    }


    @OnClick(R.id.bt_login)
    void login() {
        if(isRightPhoneNumber&&isRightPassword){
            btLogin.setProgress(30);
            stringRequest=new StringRequest(Request.Method.POST, Urls.USER_SINGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    try {
                        JSONObject object=new JSONObject(s);
                        if(object.getInt("result")==200){
                            btLogin.setProgress(100);
                            UserInfo userInfo=new UserInfo();
                            userInfo.setId(1l);
                            userInfo.setLevel(1);
                            userInfo.setNickname(object.getString("nickname"));
                            userInfo.setUsername(object.getString("username"));
                            userInfo.setHeadimg_url(object.getString("headpic"));
                            userInfo.setSex(object.getString("gender"));
                            userInfo.setSchool(object.getString("user_school"));
                            userInfo.setGrade(object.getString("user_grade"));
                            userInfo.setInstitute(object.getString("user_xueyuan"));
                            userInfo.setPhoneNumber(object.getString("phone_num"));
                            userInfo.setAge(object.getString("user_age"));
                            userInfo.setCity(object.getString("user_city"));

                            MyApplication.getInstance().getDaoSession().deleteAll(UserInfo.class);
                            MyApplication.getInstance().getDaoSession().insert(userInfo);
                            MyApplication.getInstance().setUserInfo(userInfo);
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            LoginActivity.this.finish();
                        }else{
                            btLogin.setProgress(-1);
                            Snackbar.make(btLogin,object.getString("desc"),Snackbar.LENGTH_LONG).show();
                            Log.i("TAG", object.getString("desc"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        btLogin.setProgress(-1);
                        Snackbar.make(btLogin,e.toString(),Snackbar.LENGTH_LONG).show();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    btLogin.setProgress(-1);
                    Snackbar.make(btLogin,volleyError.toString(),Snackbar.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map=new HashMap<>();
                    map.put("user_name",etUsername.getText().toString().trim());
                    String pwd_md5 = new MySecurity().encodyByMD5(etPassword.getText().toString());
                    map.put("password",pwd_md5);
                    return map;
                }
            };

            try {
                stringRequest.getHeaders();
            } catch (AuthFailureError authFailureError) {
                authFailureError.printStackTrace();
            }

            Log.i("TAG", stringRequest.getBodyContentType().toString());



            getVolleyQueue().add(stringRequest);
        }
    }

    @OnClick(R.id.tv_forgetpass)
    void findPass() {
        Intent intent = new Intent(this, ForgetPassActivity.class);
        startActivity(intent);
        finish();
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





    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
