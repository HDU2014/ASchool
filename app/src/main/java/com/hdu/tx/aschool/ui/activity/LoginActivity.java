package com.hdu.tx.aschool.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.ui.IEvent.IEventView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2015/8/12.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, IEventView {
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


        EditText passet=tilPass.getEditText();
        passet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <=6) {
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
        Intent intent=new Intent(this,ForgetPassActivity.class);
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
}
