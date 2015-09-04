package com.hdu.tx.aschool.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.BaseFragment;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.JSONHandler;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.net.Urls;
import com.hdu.tx.aschool.ui.adapter.OtherDynamicAdapter;

import org.json.JSONArray;
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
public class DynamicFragment extends BaseFragment {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private View DynamicView;
    public BaseActivity superActiviyt;
    private List<ActInfo> actInfosData;
    public OtherDynamicAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.superActiviyt = (BaseActivity) getActivity();
        superActiviyt=(BaseActivity)getActivity();
        actInfosData = new ArrayList<>();
        manager=new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(manager);

//        getActivtyData();
        initGetAct();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DynamicView = inflater.inflate(R.layout.dynamic_listcard, container, false);
        ButterKnife.bind(this, DynamicView);

        return DynamicView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void initGetAct() {
        Map<String, String> map = new HashMap<>();
        map.put("user_name", MyApplication.getInstance().getUserInfo().getUsername());
        new MyStringRequest(Urls.ACTIVITY_DYNAMIC, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                List<ActInfo> infos = JSONHandler.json2ListAct(json);
                actInfosData = infos;
                adapter = new OtherDynamicAdapter(DynamicFragment.this.getActivity(), actInfosData);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void error(String desc) {

            }

            @Override
            public Map<String, String> setParams() {
                Map<String, String> map = new HashMap<>();
                map.put("user_name", MyApplication.getInstance().getUserInfo().getUsername());
                return map;
            }
        });
    }

}
