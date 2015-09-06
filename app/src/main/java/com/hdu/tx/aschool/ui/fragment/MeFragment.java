package com.hdu.tx.aschool.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.BaseFragment;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.ui.activity.LoginActivity;
import com.hdu.tx.aschool.ui.activity.MyActivity;
import com.hdu.tx.aschool.ui.activity.MyInfoActivity;
import com.hdu.tx.aschool.ui.widget.image.CircleImageView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/8/14.
 */
public class MeFragment extends BaseFragment {


    @Bind(R.id.head_iv)
    CircleImageView headIv;
    @Bind(R.id.nickname_tv)
    TextView nicknameTv;
    @Bind(R.id.push_ll)
    LinearLayout pushLl;
    @Bind(R.id.apply_ll)
    LinearLayout applyLl;
    @Bind(R.id.collect_ll)
    LinearLayout collectLl;
    @Bind(R.id.follow_ll)
    LinearLayout followLl;
    @Bind(R.id.comment_ll)
    LinearLayout commentLl;
    @Bind(R.id.info_ll)
    LinearLayout infoLl;
    @Bind(R.id.suggestion_ll)
    LinearLayout suggestionLl;
    @Bind(R.id.my_start)
    TextView myStart;
    @Bind(R.id.my_enroll)
    TextView myEnroll;
    @Bind(R.id.my_collect)
    TextView myCollect;
    @Bind(R.id.my_foucs)
    TextView myFoucs;
    @Bind(R.id.my_comment)
    TextView myComment;
    @Bind(R.id.my_infomation)
    TextView myInfomation;
    @Bind(R.id.my_tuchao)
    TextView myTuchao;
    @Bind(R.id.back_img)
    RelativeLayout backImg;
    private UserInfo info;
    BaseActivity superActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mytool, container, false);
        ButterKnife.bind(this, view);
        backImg.getBackground().setAlpha(100);
        this.superActivity = (BaseActivity) getActivity();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        info = MyApplication.getInstance().getUserInfo();
        if (info.getLevel() == 1) {
            Picasso.with(getActivity()).load(MyApplication.getInstance().getUserInfo().getHeadimg_url()).into(headIv);
            nicknameTv.setText(info.getNickname());
        } else {
            nicknameTv.setText("未登陆");
        }
    }

    @OnClick(R.id.head_iv)
    void gotoInfo() {
        if (info.getLevel() == 0) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        } else {
            startActivity(new Intent(getActivity(), MyInfoActivity.class));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.my_start)
    void myStart() {
        Intent intent = new Intent(superActivity, MyActivity.class);
        intent.putExtra("name", "MyStart");
        startActivity(intent);
    }

    @OnClick(R.id.my_enroll)
    public void myEnroll() {
        Intent intent = new Intent(superActivity, MyActivity.class);
        intent.putExtra("name", "MyEnroll");
        startActivity(intent);
    }

    @OnClick(R.id.my_collect)
    public void setMyCollect() {

        Intent intent = new Intent(superActivity, MyActivity.class);
        intent.putExtra("name", "MyCollect");
        startActivity(intent);
    }

    @OnClick(R.id.my_infomation)
    public void setMyInfomation() {
        Intent intent = MyApplication.getInstance().getUserInfo().getLevel() == 0 ?
                new Intent(superActivity, LoginActivity.class) :
                new Intent(superActivity, MyInfoActivity.class);
        superActivity.startActivity(intent);
    }
}
