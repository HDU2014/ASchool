package com.hdu.tx.aschool.ui.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.BaseFragment;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.common.utils.MyStrings;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.JSONHandler;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.net.Urls;
import com.hdu.tx.aschool.ui.activity.AdDetailActivity;
import com.hdu.tx.aschool.ui.adapter.OfficeAdapter;
import com.hdu.tx.aschool.ui.widget.pupuwindow.SelectItemsPop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/8/23.
 */
public class OfficeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG="OfficeFragment";
    @Bind(R.id.schools)
    TextView schools;
    @Bind(R.id.types)
    TextView types;
    @Bind(R.id.time)
    TextView time;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private View rootView;
    private BaseActivity superActivity;
    private List<ActInfo> adapterData;
    private OfficeAdapter adapter;
    private LinearLayoutManager manager;
    private boolean isSliding;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ad, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.superActivity= (BaseActivity) getActivity();
        manager=new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setOnScrollListener(scrolllistener);

        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefresh.setDistanceToTriggerSync(100);// 设置下拉距离
        swipeRefresh.setSize(SwipeRefreshLayout.LARGE);

        initGetAct();
    }

    private boolean isInitComplect;
    RecyclerView.OnScrollListener scrolllistener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                //获取最后一个完全显示的ItemPosition
                int lastVisibleItem = manager.findLastVisibleItemPosition();
                // 判断是否滚动到底部，并且是向右滚动
                if (lastVisibleItem == (adapterData.size()-1) && isSliding) {
                    //加载更多功能的代码
                    if(isInitComplect)
                    getMoreAct();
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (manager.findFirstCompletelyVisibleItemPosition() == 0) {
                swipeRefresh.setEnabled(true);
                Log.d(TAG, "swipe_refresh.setEnabled(true)");
            } else {
                Log.d(TAG,"swipe_refresh.setEnabled(false)");
                swipeRefresh.setEnabled(false);
            }
            if (dy > 0) {
                isSliding = true;
                Log.d(TAG,"isSliding==true");
            } else {
                Log.d(TAG,"isSliding==false");
                isSliding = false;
            }
        }
    };


    public List<ActInfo> getData() {
        List<ActInfo> datas = new ArrayList<ActInfo>();
        String[] address = getResources().getStringArray(R.array.activity_schools);
        String[] title = getResources().getStringArray(R.array.activity_type);
        for (int i = 0; i < 20; i++) {
            ActInfo info = new ActInfo();
            info.setImageUrl(MyStrings.AVATARS[new Random().nextInt(12)]);
            info.setTitle(title[new Random().nextInt(title.length - 1)]);
            info.setTotalpeopel(i * 100);
            info.setJoinedpeopel(i * i);
            info.setCollectTimes(i * 4);
            info.setAddress(address[new Random().nextInt(7) + 1]);
            info.setHostname("主办方" + i);
            info.setTime("2015-8-28 12:" + i);
            datas.add(info);
        }
        return datas;
    }

    @Override
    public void onRefresh() {
        swipeRefresh.setRefreshing(true);
        initGetAct();
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.schools)
    void onclick1() {
        final ArrayList<String> schoolsData = new ArrayList<>();
        schoolsData.add("不限学校");
        schoolsData.add("杭州电子科技大学");
        schoolsData.add("浙江理工大学");
        schoolsData.add("浙江传媒学院");
        schoolsData.add("中国计量学院");
        schoolsData.add("浙江水利水电学院");
        schoolsData.add("杭州师范大学");
        schoolsData.add("浙江工商大学");
        final SelectItemsPop pop = new SelectItemsPop(getActivity(), schoolsData, schools);

        pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                schools.setText(schoolsData.get(position));
                pop.dismiss();
            }
        });
    }


    @OnClick(R.id.types)
    void onclick2() {
        final ArrayList<String> schoolsData = new ArrayList<>();
        schoolsData.add("不限类型");
        schoolsData.add("运动");
        schoolsData.add("讲座");
        schoolsData.add("节日");
        schoolsData.add("创业");
        schoolsData.add("旅行");
        schoolsData.add("文艺");
        schoolsData.add("棋牌");
        schoolsData.add("骑行");
        schoolsData.add("招聘");
        schoolsData.add("沙龙");
        schoolsData.add("其他");
        final SelectItemsPop pop = new SelectItemsPop(getActivity(), schoolsData, types);

        pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                types.setText(schoolsData.get(position));
                pop.dismiss();
            }
        });
    }

    @OnClick(R.id.time)
    void onclick3() {
        final ArrayList<String> schoolsData = new ArrayList<>();
        schoolsData.add("不限时间");
        schoolsData.add("3小时内");
        schoolsData.add("1天内");
        schoolsData.add("1周内");
        schoolsData.add("1个月内");
        schoolsData.add("其他");
        final SelectItemsPop pop = new SelectItemsPop(getActivity(), schoolsData, time);

        pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                time.setText(schoolsData.get(position));
                pop.dismiss();
            }
        });
    }


    public void getMoreAct(){

        new MyStringRequest(Urls.ACTIVITY_QUERY_MUTI, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                    swipeRefresh.setRefreshing(false);
                    List<ActInfo> infos= JSONHandler.json2ListAct(json);
                    adapterData.addAll(infos);
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void error(String desc) {

            }

            @Override
            public Map<String, String> setParams() {
                Map<String,String> map=new HashMap<>();
                map.put("last_aid",adapterData.get(adapterData.size()-1).getAid());
                map.put("act_num", "15");
                return map;
            }
        });
    }


    public void initGetAct(){
        new MyStringRequest(Urls.ACTIVITY_QUERY_MUTI, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                    Log.i("TAG",json.toString());
                    if(swipeRefresh!=null)swipeRefresh.setRefreshing(false);
                    List<ActInfo> infos= JSONHandler.json2ListAct(json);
                    adapterData=infos;
                    adapter=new OfficeAdapter(OfficeFragment.this.getActivity(), adapterData);
                    recyclerView.setAdapter(adapter);
                    isInitComplect=true;
            }

            @Override
            public void error(String desc) {}

            @Override
            public Map<String, String> setParams() {
                Map<String,String> map=new HashMap<>();
                map.put("last_aid","-1");
                map.put("act_num", "15");
                return map;
            }
        });
    }
}
