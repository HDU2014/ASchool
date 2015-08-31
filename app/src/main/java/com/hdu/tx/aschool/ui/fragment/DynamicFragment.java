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

    //    public void getActivtyData() {
//
//
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.GET_PUBLISH_ACTIVITY, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                try {
//                    JSONObject object=new JSONObject(s);
//                    if(object.getInt("result")==200){
//
//
//                        Picasso.with(superActiviyt).load(object.getString("act_img")).into(eventImage);
//                        eventTitle.setText(object.getString("title"));
//                        host.setText(object.getString("host_name"));
//                        eventTime.setText(object.getString("start_time"));
//                        eventAddress.setText(object.getString("act_place"));
//                        eventCollect.setText(object.getString("collect_num"));
//
//
//                    }else{
////                           toast(toolbar,object.getString("desc"));
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    // toast(toolbar,e.toString());
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> map=new HashMap<>();
//                map.put("user_name", MyApplication.getInstance().getUserInfo().getUsername());
//                return map;
//            }
//        };
//        superActiviyt.getVolleyQueue().add(stringRequest);
//    }
    public void initGetAct() {
        Map<String, String> map = new HashMap<>();
        map.put("user_name", MyApplication.getInstance().getUserInfo().getUsername());
        new MyStringRequest(Urls.GET_DYNAMIC_ACTIVITY, map, new InternetListener() {
            @Override
            public void success(JSONObject desc) {

                List<ActInfo> infos = new ArrayList<>();
                JSONArray array = null;
                try {
                    array = desc.getJSONArray("activities");
                    for (int i = 0; i < array.length(); i++) {
                        ActInfo info = new ActInfo();
                        JSONObject infoObject = array.getJSONObject(i);
                        info.setTitle(infoObject.getString("title"));
                        info.setTime(infoObject.getString("start_time"));
                        info.setTotalpeopel(infoObject.getInt("act_num"));
                        info.setAddress(infoObject.getString("act_place"));
                        info.setDescribe(infoObject.getString("content"));
                        info.setHostname("zhubanfang");
                        info.setJoinedpeopel(infoObject.getInt("join_num"));
                        info.setCollectTimes(infoObject.getInt("collect_num"));
                        info.setLookTimes(infoObject.getInt("browse_num"));
                        info.setImageUrl(infoObject.getString("act_img"));
                        infos.add(info);
                        Log.v("MSG",infoObject.getString("title"));
                    }
                    actInfosData = infos;
                    adapter = new OtherDynamicAdapter(DynamicFragment.this.getActivity(), actInfosData);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String desc) {

            }
        });


    }
}
