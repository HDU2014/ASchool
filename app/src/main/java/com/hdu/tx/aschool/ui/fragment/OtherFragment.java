package com.hdu.tx.aschool.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pualgo on 2015/8/28.
 */

public class OtherFragment extends BaseFragment {
    public View otherView;
    @Bind(R.id.school_user_info)
    TextView schoolUserInfo;
    @Bind(R.id.academy_user_info)
    TextView academyUserInfo;
    @Bind(R.id.grade_user_info)
    TextView gradeUserInfo;
    @Bind(R.id.subscribe_user_info)
    TextView subscribeUserInfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        otherView = inflater.inflate(R.layout.user_info_sub, container, false);

        ButterKnife.bind(this, otherView);
        return otherView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
