package com.hdu.tx.aschool.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.common.view.EditActivity;
import com.hdu.tx.aschool.dao.DaoSession;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.ui.widget.image.CircleImageView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/8/14.
 */
public class MyInfoActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.head_img)
    CircleImageView headImg;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.nickname_ll)
    LinearLayout nicknameLl;
    @Bind(R.id.sex_ll)
    LinearLayout sexll;
    @Bind(R.id.sex_tv)
    TextView sextv;
    @Bind(R.id.nickname_tv)
    TextView nicknameTv;
    @Bind(R.id.school_tv)
    TextView schoolTv;
    @Bind(R.id.school_ll)
    LinearLayout schoolLl;
    @Bind(R.id.grade_tv)
    TextView gradeTv;
    @Bind(R.id.grade_ll)
    LinearLayout gradeLl;
    @Bind(R.id.insititude_tv)
    TextView insititudeTv;
    @Bind(R.id.insititude_ll)
    LinearLayout insititudeLl;
    @Bind(R.id.phone_tv)
    TextView phoneTv;
    @Bind(R.id.phone_ll)
    LinearLayout phoneLl;
    @Bind(R.id.city_tv)
    TextView cityTv;
    @Bind(R.id.city_ll)
    LinearLayout cityLl;
    @Bind(R.id.age_tv)
    TextView ageTv;
    @Bind(R.id.age_ll)
    LinearLayout ageLl;

    private Intent intent;
    private Bundle bundle;
    private UserInfo userInfo;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.my_info);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        intent = new Intent(MyInfoActivity.this, EditActivity.class);
        bundle = new Bundle();
        userInfo= MyApplication.getInstance().getUserInfo();
        daoSession= MyApplication.getInstance().getDaoSession();

        refreshMyInfo();




    }

    private void refreshMyInfo() {
        Picasso.with(this).load(userInfo.getHeadimg_url()).into(headImg);
        nicknameTv.setText(userInfo.getNickname());
        Log.i("TAG",userInfo.getHeadimg_url());
        sextv.setText(userInfo.getSex());
        ageTv.setText(userInfo.getAge());
        schoolTv.setText(userInfo.getSchool());
        gradeTv.setText(userInfo.getGrade());
        insititudeTv.setText(userInfo.getInstitute());
        phoneTv.setText(userInfo.getPhoneNumber());
        cityTv.setText(userInfo.getCity());
    }

    @OnClick(R.id.nickname_ll)void setNickname(){

        bundle.putString("title", "修改昵称");
        bundle.putString("descript", "修改昵称让好友们都记住你吧！");
        bundle.putString("hint", "输入你的昵称");
        intent.putExtras(bundle);
        MyInfoActivity.this.startActivity(intent);
    }

    @OnClick(R.id.sex_ll)void setSex(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MyInfoActivity.this);
        builder.setTitle("请选择性别");
        final String[] sex = {"男", "女"};
        builder.setItems(sex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sextv.setText(sex[which]);
                userInfo.setSex(sex[which]);
                daoSession.update(userInfo);
            }
        });
        builder.show();
    }

    @OnClick(R.id.age_ll)void setAge(){
        bundle.putString("title", "修改年龄");
        bundle.putString("descript", "修改你的年龄让我们为你推荐最好的活动");
        bundle.putString("hint", "输入你的年龄");
        intent.putExtras(bundle);
        MyInfoActivity.this.startActivity(intent);
    }

    @OnClick(R.id.school_ll)void setSchool(){
        bundle.putString("title", "修改学校");
        bundle.putString("descript", "修改你的学校让更多的校友发现你们");
        bundle.putString("hint", "输入你的学校");
        intent.putExtras(bundle);
        MyInfoActivity.this.startActivity(intent);
    }

    @OnClick(R.id.grade_ll)void setGrade(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MyInfoActivity.this);
        builder.setTitle("请选择年级");
        final String[] grades = getResources().getStringArray(R.array.grades);
        builder.setItems(grades, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gradeTv.setText(grades[which]);
                userInfo.setGrade(grades[which]);
                daoSession.update(userInfo);
            }
        });
        builder.show();
    }

    @OnClick(R.id.insititude_ll)void setInsititude(){
        bundle.putString("title", "修改学院");
        bundle.putString("descript", "修改你的学院让你认识更多的朋友");
        bundle.putString("hint", "输入你的学院");
        intent.putExtras(bundle);
        MyInfoActivity.this.startActivity(intent);
    }

    @OnClick(R.id.phone_ll)void setPhone(){
        bundle.putString("title", "修改手机号");
        bundle.putString("descript", "修改你的手机号让大家方便联系你");
        bundle.putString("hint", "输入你的手机号");
        intent.putExtras(bundle);
        MyInfoActivity.this.startActivity(intent);
    }

    @OnClick(R.id.city_ll)void setCity(){
        bundle.putString("title", "修改城市");
        bundle.putString("descript", "修改你的城市让同城的伙伴嗨起来");
        bundle.putString("hint", "输入你所在的城市");
        intent.putExtras(bundle);
        MyInfoActivity.this.startActivity(intent);
    }
}
