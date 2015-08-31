package com.hdu.tx.aschool.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.common.utils.MySecurity;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.JSONHandler;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.net.Urls;
import com.hdu.tx.aschool.ui.adapter.FragmentAdapter;
import com.hdu.tx.aschool.ui.fragment.DynamicFragment;
import com.hdu.tx.aschool.ui.fragment.OtherFragment;
import com.hdu.tx.aschool.ui.widget.image.CircleImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pualgo on 2015/8/28.
 */
public class OtherInfoActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.other_userid)
    TextView otherUserid;
    @Bind(R.id.other_head_user)
    CircleImageView otherHeadUser;
    @Bind(R.id.other_foucs_num)
    TextView otherFoucsNum;
    @Bind(R.id.other_fans_num)
    TextView otherFansNum;
    @Bind(R.id.other_chat)
    TextView otherChat;
    @Bind(R.id.other_foucs)
    TextView otherFoucs;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.user_tablayout)
    TabLayout userTablayout;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    public String searchIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_info);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        initView();
        String hostid=getIntent().getStringExtra("host_id");

        getUserInfo(hostid);
    }

    public void initView() {

        List<String> title = new ArrayList<>();
        title.add("信息");
        title.add("动态");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new OtherFragment());
        fragments.add(new DynamicFragment());
//        fragments.add(new OfficeFragment());
//        fragments.add(new MainFragment());


        FragmentAdapter adapter = new FragmentAdapter(this.getSupportFragmentManager(), fragments, title);
        viewpager.setAdapter(adapter);
        userTablayout.addTab(userTablayout.newTab().setText(title.get(0)));
        userTablayout.addTab(userTablayout.newTab().setText(title.get(1)));

        userTablayout.setupWithViewPager(viewpager);
        userTablayout.setTabsFromPagerAdapter(adapter);
        viewpager.setCurrentItem(0);
        //获取用户名称
//        Intent intent = this.getIntent();
//        searchIndex=(String) intent.getSerializableExtra("HostName");

    }

    public void getUserInfo(final String  hostid) {
//        Map<String,String> map=new HashMap<>();
//        map.put("user_id", hostid);
//        new MyStringRequest(Urls.QUERY_UERINFO_BYID, map, new InternetListener() {
//            @Override
//            public void success(JSONObject json) {
//                try {
//                    UserInfo userInfo= JSONHandler.json2UserInfo(json);
//                    otherUserid.setText(userInfo.getNickname());
//                    Picasso.with(OtherInfoActivity.this).load(userInfo.getHeadimg_url()).into(otherHeadUser);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void error(String desc) {
//
//            }
//        });
    }

}
