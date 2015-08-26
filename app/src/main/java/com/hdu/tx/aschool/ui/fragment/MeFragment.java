package com.hdu.tx.aschool.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseFragment;
import com.hdu.tx.aschool.ui.widget.image.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
