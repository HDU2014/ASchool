package com.hdu.tx.aschool.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseFragment;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.dao.UserInfo;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mytool, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UserInfo info= MyApplication.getInstance().getUserInfo();
        if(info.getLevel()==1){
            Picasso.with(getActivity()).load(MyApplication.getInstance().getUserInfo().getHeadimg_url()).into(headIv);
            nicknameTv.setText(info.getNickname());
        }
    }

    @OnClick(R.id.head_iv)void gotoInfo(){
        startActivity(new Intent(getActivity(), MyInfoActivity.class));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
