package com.hdu.tx.aschool.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.ui.widget.image.CircleImageView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/8/21.
 */
public class AdDetailActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.act_img)
    ImageView actImg;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.look_tv)
    TextView lookTv;
    @Bind(R.id.collect_tv)
    TextView collectTv;
    @Bind(R.id.time_tv)
    TextView timeTv;
    @Bind(R.id.address_tv)
    TextView addressTv;
    @Bind(R.id.person_num)
    TextView personNum;
    @Bind(R.id.head_img)
    CircleImageView headImg;
    @Bind(R.id.nickname)
    TextView nickname;
    @Bind(R.id.hostname_tv)
    TextView hostnameTv;
    @Bind(R.id.nickname_ll)
    LinearLayout nicknameLl;
    @Bind(R.id.describe_tv)
    TextView describeTv;
    @Bind(R.id.lunboll)
    LinearLayout lunboll;
    @Bind(R.id.nsv)
    NestedScrollView nsv;
    @Bind(R.id.collect)
    ImageView collect;
    @Bind(R.id.bt)
    LinearLayout bt;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adetail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        collapsingToolbar.setTitle("活动详情");
        init();
    }

    public void init() {
        ActInfo actInfo= (ActInfo) getIntent().getSerializableExtra("activity");
        if(actInfo==null)return;
        String path=actInfo.getImageUrl();
        if(path!=null&&!"".equals(path))
        Picasso.with(this).load(actInfo.getImageUrl()).into(actImg);
        title.setText(actInfo.getTitle());
        timeTv.setText(actInfo.getTime());
        addressTv.setText(actInfo.getAddress());
        personNum.setText(actInfo.getJoinedpeopel() + "/" + actInfo.getTotalpeopel());
        hostnameTv.setText(actInfo.getHostname());
        describeTv.setText(actInfo.getDescribe());
       // MyApplication.getInstance().getDaoSession().insert(actInfo);
       // Snackbar.make(toolbar,"数据库插入成功",Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.collect)void setCollect(){
        if(collect.isSelected())collect.setSelected(false);
        else collect.setSelected(true);
    }
    @OnClick(R.id.hostname_tv)void getDetail() {
        Intent intent=new Intent(AdDetailActivity.this,OtherInfoActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("HostNane",hostnameTv.getText().toString());
        intent.putExtras(bundle);
        AdDetailActivity.this.startActivity(intent);

    }
}
