package com.hdu.tx.aschool.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.BaseFragment;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.net.Urls;
import com.hdu.tx.aschool.ui.activity.OtherInfoActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
    private BaseActivity superActivity;

    private UserInfo userInfo;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.superActivity= (BaseActivity) getActivity();

       // getData();
    }

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


    public void getData() {
        new MyStringRequest(Urls.USER_QUERY_BYID, new InternetListener() {
            @Override
            public void success(JSONObject json) {

            }

            @Override
            public void error(String desc) {

            }

            @Override
            public Map<String, String> setParams() {
                Map<String,String> map=new HashMap<>();
                map.put("user_name","13336931879");
                return  map;
            }
        });
    }



}
