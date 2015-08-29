package com.hdu.tx.aschool.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pualgo on 2015/8/28.
 */
public class DynamicFragment extends BaseFragment {
    @Bind(R.id.event_image)
    ImageView eventImage;
    @Bind(R.id.event_title)
    TextView eventTitle;
    @Bind(R.id.dynamic_type)
    TextView dynamicType;
    @Bind(R.id.host)
    TextView host;
    @Bind(R.id.event_time)
    TextView eventTime;
    @Bind(R.id.event_address)
    TextView eventAddress;
    @Bind(R.id.collect_img)
    ImageView collectImg;
    @Bind(R.id.event_collect)
    TextView eventCollect;
    @Bind(R.id.event_joined)
    TextView eventJoined;
    @Bind(R.id.article_cardview)
    CardView articleCardview;
    private View DynamicView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DynamicView = inflater.inflate(R.layout.dynamic_cardview, container, false);
        ButterKnife.bind(this, DynamicView);
        //yyyyyyyy
        return DynamicView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    public  void dis(){}
}
