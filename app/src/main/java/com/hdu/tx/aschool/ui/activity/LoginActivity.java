package com.hdu.tx.aschool.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;
import com.dd.processbutton.iml.ActionProcessButton;
import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.common.utils.MySecurity;
import com.hdu.tx.aschool.dao.DaoSession;
import com.hdu.tx.aschool.dao.InputString;
import com.hdu.tx.aschool.dao.InputStringDao;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.easemod.utils.CommonUtils;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.JSONHandler;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.net.Urls;
import com.hdu.tx.aschool.ui.IEvent.IEventView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2015/8/12.
 */
public class LoginActivity extends BaseActivity implements IEventView{
    private static final String TAG ="LoginActivity" ;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.bt_login)
    ActionProcessButton btLogin;

    @Bind(R.id.password_et)
    EditText etPassword;
    @Bind(R.id.username_actv)
    AutoCompleteTextView usernameActv;

    private DaoSession daoSession;


    @OnClick({R.id.iv_qq, R.id.iv_weixin, R.id.iv_weibo})
    void select(ImageView imageView) {
        selectThirdAccount(imageView);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        daoSession=MyApplication.getInstance().getDaoSession();
        initToolbar();
        initAutoComplete();
        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.bt_login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
    }


    private void initAutoComplete(){
        List<InputString> inputStringList=daoSession.getInputStringDao().queryBuilder().where(InputStringDao.Properties.Type.eq(1)).list();
        String[] arr=new String[inputStringList.size()];
        for (int i = 0; i <inputStringList.size() ; i++) {
            arr[i]=inputStringList.get(i).getText();
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);
        usernameActv.setAdapter(arrayAdapter);
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


    private void attemptLogin() {

        hideInput();
        // Reset errors.
        usernameActv.setError(null);
        etPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = usernameActv.getText().toString();
        String password = etPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && password.length()<4) {
            etPassword.setError("密码长度太短");
            focusView = etPassword;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            usernameActv.setError("用户名不能为空");
            focusView = usernameActv;
            cancel = true;
        } else if (email.length()!=11) {
            usernameActv.setError("用户名格式不正确");
            focusView = usernameActv;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
           login();
        }
    }


    /**
     * 登录服务器
     */
    public void login() {
            btLogin.setProgress(30);
            new MyStringRequest(Urls.USER_LOGIN, new InternetListener() {
                @Override
                public void success(JSONObject json) {
                    btLogin.setProgress(99);
                    UserInfo userInfo= JSONHandler.json2UserInfo(json);
                    userInfo.setId(1l);
                    userInfo.setLevel(1);
                    userInfo.setLoadTimes(2);
                    MyApplication.getInstance().getDaoSession().deleteAll(UserInfo.class);
                    MyApplication.getInstance().getDaoSession().insert(userInfo);
                    MyApplication.getInstance().setUserInfo(userInfo);
                    loginEMChat(usernameActv.getText().toString().trim(), new MySecurity().encodyByMD5(etPassword.getText().toString()));
                    addAutoComplete();
                }
                @Override
                public void error(String desc) {
                    btLogin.setProgress(-1);
                    Snackbar.make(btLogin,desc,Snackbar.LENGTH_LONG).show();
                }
                @Override
                public Map<String, String> setParams() {
                    Map<String,String> map=new HashMap<>();
                    map.put("user_name",usernameActv.getText().toString().trim());
                    String pwd_md5 = new MySecurity().encodyByMD5(etPassword.getText().toString());
                    map.put("password",pwd_md5);
                    return map;
                }
            });
    }


    public void addAutoComplete(){
        List<InputString> inputStringList=daoSession.getInputStringDao().queryBuilder().where(InputStringDao.Properties.Type.eq(1)).list();
        boolean exist=false;
        for(InputString s:inputStringList){
            if(s.getText().equals(usernameActv.getText().toString().trim()))exist=true;
        }
        if(!exist){
            InputString in=new InputString();
            in.setType(1);
            in.setText(usernameActv.getText().toString());
            daoSession.getInputStringDao().insert(in);
        }
    }

    /**
     * 隐藏输入法
     */
    private void hideInput(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 忘记密码点击事件
     */
    @OnClick(R.id.tv_forgetpass)
    void findPass() {
        Intent intent = new Intent(this, ForgetPassActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 注册点击事件
     */
    @OnClick(R.id.tv_regist)
    void regist(){
        Intent intent = new Intent(this, RegistActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 登录按钮点击事件
     */
    @OnClick(R.id.bt_login)
    void click(){
        attemptLogin();
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


    /**
     * 登录环信服务器
     * @param user  用户名
     * @param pass  密码(MD5加密)
     */
    public void loginEMChat(final String user, final String pass){
        if (!CommonUtils.isNetWorkConnected(this)) {
            Snackbar.make(toolbar, R.string.network_isnot_available, Snackbar.LENGTH_SHORT).show();
            return;
        }

        EMChatManager.getInstance().login(user, pass, new EMCallBack() {
            @Override
            public void onSuccess() {
                Message msg=new Message();
                msg.what=100;
                handler.sendMessage(msg);
                MyApplication.getInstance().login(user,pass);
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                LoginActivity.this.finish();
            }

            @Override
            public void onError(int i, String s) {
                toast(toolbar,s);
                Message msg=new Message();
                msg.what=-1;
                handler.sendMessage(msg);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }


    /**
     * 消息处理
     */
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            btLogin.setProgress(msg.what);
        }
    };


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
