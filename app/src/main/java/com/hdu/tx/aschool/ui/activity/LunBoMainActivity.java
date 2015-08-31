package com.hdu.tx.aschool.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.JSONHandler;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.net.Urls;
import com.hdu.tx.aschool.ui.adapter.LunBoAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LunBoMainActivity extends BaseActivity {


    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    RelativeLayout authorLayout;
    @Bind(R.id.v_dot0)
    View vDot0;
    @Bind(R.id.v_dot1)
    View vDot1;
    @Bind(R.id.v_dot2)
    View vDot2;
    @Bind(R.id.v_dot3)
    View vDot3;
    @Bind(R.id.v_dot4)
    View vDot4;
    @Bind(R.id.lunboll)
    LinearLayout lunboll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lunboactivity_main);
        ButterKnife.bind(this);
        //getLunBoAct();
        LunBoAdapter adapter=new LunBoAdapter(LunBoMainActivity.this,lunboll,getBannerAd());

    }


    public void getLunBoAct(){

    }


    public static List<ActInfo> getBannerAd() {
        List<ActInfo> adList = new ArrayList<ActInfo>();

        ActInfo adDomain = new ActInfo();

        adDomain.setTime("3月4日");
        adDomain.setTitle("我和令计划只是同姓");
        adDomain.setDescribe("我想知道令狐安和令计划有什么关系？");
        adDomain.setImageUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=bb99d6add2c8a786be2a4c0f5708c9c7/d50735fae6cd7b8900d74cd40c2442a7d9330e29.jpg");
        adList.add(adDomain);


        ActInfo adDomain1 = new ActInfo();
        adDomain1.setTime("3月5日");
        adDomain1.setTitle("我和令计划只是同姓");
        adDomain1.setDescribe("我想知道令狐安和令计划有什么关系？");
        adDomain1.setImageUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=7cbcd7da78f40ad115e4c1e2672e1151/eaf81a4c510fd9f9a1edb58b262dd42a2934a45e.jpg");
        adList.add(adDomain1);

        ActInfo adDomain2 = new ActInfo();
        adDomain2.setTime("3月6日");
        adDomain2.setTitle("我和令计划只是同姓");
        adDomain2.setDescribe("我想知道令狐安和令计划有什么关系？");
        adDomain2.setImageUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=bb99d6add2c8a786be2a4c0f5708c9c7/d50735fae6cd7b8900d74cd40c2442a7d9330e29.jpg");
        adList.add(adDomain2);

        ActInfo adDomain3 = new ActInfo();
        adDomain3.setTime("3月7日");
        adDomain3.setTitle("我和令计划只是同姓");
        adDomain3.setDescribe("我想知道令狐安和令计划有什么关系？");
        adDomain3.setImageUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=bb99d6add2c8a786be2a4c0f5708c9c7/d50735fae6cd7b8900d74cd40c2442a7d9330e29.jpg");
        adList.add(adDomain3);

        ActInfo adDomain4 = new ActInfo();
        adDomain4.setTime("3月8日");
        adDomain4.setTitle("我和令计划只是同姓");
        adDomain4.setDescribe("我想知道令狐安和令计划有什么关系？");
        adDomain4.setImageUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=bb99d6add2c8a786be2a4c0f5708c9c7/d50735fae6cd7b8900d74cd40c2442a7d9330e29.jpg");
        adList.add(adDomain4);

        return adList;
    }

}
