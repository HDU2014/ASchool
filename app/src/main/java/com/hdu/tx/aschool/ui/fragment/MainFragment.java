package com.hdu.tx.aschool.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseFragment;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.JSONHandler;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.net.Urls;
import com.hdu.tx.aschool.ui.IEvent.LunBoOnClickListener;
import com.hdu.tx.aschool.ui.View.TagsView;
import com.hdu.tx.aschool.ui.activity.AdDetailActivity;
import com.hdu.tx.aschool.ui.activity.MainActivity;
import com.hdu.tx.aschool.ui.adapter.LunBoAdapter;
import com.hdu.tx.aschool.ui.adapter.TagAdapter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/8/18.
 */
public class MainFragment extends BaseFragment {


    private static final String TAG = "MainFragment";
    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.v_dot0)
    View vDot0;
    @Bind(R.id.v_dot1)
    View vDot1;
    @Bind(R.id.v_dot2)
    View vDot2;
    @Bind(R.id.v_dot3)
    View vDot3;
    @Bind(R.id.v_dot4)
    View vDot4;
    @Bind(R.id.lunboll)
    LinearLayout lunboll;
    @Bind(R.id.linear_layout)
    LinearLayout linearLayout;
    @Bind(R.id.nsv)
    NestedScrollView nsv;
    @Bind(R.id.tags_view)
    TagsView tagsView;

    private View rootView;

    private TagAdapter tagAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        tagsView.initView();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLunBoAct();
        initGetAct();
    }

    public void initGetAct() {

        new MyStringRequest(Urls.ACTIVITY_QUERY_MUTI, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                List<ActInfo> infos = JSONHandler.json2ListAct(json);
                if(tagsView==null)return;
               tagsView.setDate(infos);
            }

            @Override
            public void error(String desc) {

            }

            @Override
            public Map<String, String> setParams() {
                Map<String, String> map = new HashMap<>();
                map.put("last_aid", "-1");
                map.put("act_num", "15");
                return map;
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void getLunBoAct() {
        new MyStringRequest(Urls.ACTIVITY_QUERY_LUNBO, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                initLunBoView(JSONHandler.json2ListAct(json));
            }

            @Override
            public void error(String desc) {
                Log.i("TAG", desc);
            }

            @Override
            public Map<String, String> setParams() {
                Map<String, String> map = new HashMap<>();
                return map;
            }
        });
    }

    public void initLunBoView(List<ActInfo> actInfos) {
        LunBoAdapter adapter = new LunBoAdapter((MainActivity) getActivity(), lunboll, actInfos);
        adapter.setLunBoOnClickListener(new LunBoOnClickListener() {
            @Override
            public void onClick(ActInfo actInfo) {
                getActivity().startActivity(new Intent(getActivity(), AdDetailActivity.class).putExtra("activity", actInfo));
            }
        });
    }

}
