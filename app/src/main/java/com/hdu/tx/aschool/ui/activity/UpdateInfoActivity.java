package com.hdu.tx.aschool.ui.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.net.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/8/18.
 */
public class UpdateInfoActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.iv_clear)
    ImageView ivClear;
    @Bind(R.id.decript)
    TextView decript;
    @Bind(R.id.btn_submit)
    ActionProcessButton btnSubmit;
    private Map<String,String> map;
    private String param;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_editactivity);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        map=new HashMap<>();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (bundle != null) {
            toolbar.setTitle(bundle.getString("title"));
            decript.setText(bundle.getString("descript"));
            etUsername.setHint(bundle.getString("hint"));
            if(bundle.getBoolean("isNumber")){
                etUsername.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
            param=bundle.getString("param");
        }


        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUsername.setText("");
            }
        });

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    ivClear.setVisibility(View.VISIBLE);
                } else {
                    ivClear.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        
    }


    @OnClick(R.id.btn_submit)void submit(){

        if(etUsername.getText().toString().equals("")){
            Snackbar.make(toolbar,R.string.input_format_error,Snackbar.LENGTH_LONG).show();
            return;
        }

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.USER_UPDATE_INFO, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject object=new JSONObject(s);
                    if(object.getInt("result")==200){
                        btnSubmit.setProgress(100);
                        UserInfo userInfo=MyApplication.getInstance().getUserInfo();
                        String value=etUsername.getText().toString().trim();
                        if(param.equals("nick_name")){
                            userInfo.setNickname(value);
                        }else if(param.equals("user_age")){
                            userInfo.setAge(value);
                        }else if(param.equals("user_city")){
                            userInfo.setCity(value);
                        }else if(param.equals("user_school")){
                            userInfo.setSchool(value);
                        }else if(param.equals("user_xueyuan")){
                            userInfo.setInstitute(value);
                        }else if(param.equals("user_grade")){
                            userInfo.setGrade(value);
                        }else if(param.equals("phone_num")){
                            userInfo.setPhoneNumber(value);
                        }
                        MyApplication.getInstance().getDaoSession().update(userInfo);
                        MyApplication.getInstance().setUserInfo(userInfo);
                        setResult(0);
                        finish();
                    }else{
                        btnSubmit.setProgress(-1);
                        Snackbar.make(toolbar,object.getString("desc"),Snackbar.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    btnSubmit.setProgress(-1);
                    Snackbar.make(toolbar,e.toString(),Snackbar.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                btnSubmit.setProgress(-1);
                Snackbar.make(toolbar,volleyError.toString(),Snackbar.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                map.put(param, etUsername.getText().toString().trim());
                map.put("user_name", MyApplication.getInstance().getUserInfo().getUsername());
                return map;
            }
        };
        getVolleyQueue().add(stringRequest);
    }
}
