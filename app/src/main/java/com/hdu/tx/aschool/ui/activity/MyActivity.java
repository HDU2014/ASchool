package com.hdu.tx.aschool.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.JSONHandler;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.net.Urls;
import com.hdu.tx.aschool.ui.adapter.OfficeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pualgo on 2015/8/30.
 */
public class MyActivity extends BaseActivity {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private LinearLayoutManager manager;
    public OfficeAdapter adapter;
    private List<ActInfo> adapterData;
    public String str;
    public String MyUrl;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
        ButterKnife.bind(this);
        dialog=new ProgressDialog(this);
        dialog.setMessage("正在加载数据，请稍后");
        dialog.show();
        str = getIntent().getStringExtra("name");
        Log.v("msg", str);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (str.equals("MyStart")) {

            getSupportActionBar().setTitle("我的发起");
            MyUrl = Urls.GET_PUBLISH_ACTIVITY;
        } else if (str.equals("MyCollect")) {
            getSupportActionBar().setTitle("我的收藏");
            MyUrl = Urls.GET_COLLECT_ACTIVITY;
        } else if (str.equals("MyEnroll")) {
            getSupportActionBar().setTitle("我的报名");
            MyUrl = Urls.GET_JOIN_ACTIVITY;
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        manager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(manager);

        initGetAct();
    }

    public void initGetAct() {
        new MyStringRequest(MyUrl, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                dialog.dismiss();
                List<ActInfo> infos = JSONHandler.json2ListAct(json);
                adapterData = infos;
                adapter = new OfficeAdapter(MyActivity.this, adapterData);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void error(String desc) {
                toast(toolbar,desc);
                dialog.dismiss();
            }

            @Override
            public Map<String, String> setParams() {
                return null;
            }
        });
    }
}
