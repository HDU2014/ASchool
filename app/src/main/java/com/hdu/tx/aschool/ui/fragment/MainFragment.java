package com.hdu.tx.aschool.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseFragment;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.entity.MyTagEntity;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.JSONHandler;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.net.Urls;
import com.hdu.tx.aschool.ui.IEvent.LunBoOnClickListener;
import com.hdu.tx.aschool.ui.View.TagView;
import com.hdu.tx.aschool.ui.activity.AdDetailActivity;
import com.hdu.tx.aschool.ui.activity.MainActivity;
import com.hdu.tx.aschool.ui.adapter.LunBoAdapter;
import com.hdu.tx.aschool.ui.adapter.TagAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/8/18.
 */
public class MainFragment extends BaseFragment {


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
    private View rootView;

    private TagView[] tagViews=new TagView[3];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        tagViews[0]= (TagView) rootView.findViewById(R.id.tag1);
        tagViews[1]= (TagView) rootView.findViewById(R.id.tag2);
        tagViews[2]= (TagView) rootView.findViewById(R.id.tag3);
        for (int i = 0; i <3 ; i++) {
            tagViews[i].initView();
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLunBoAct();
        getTagData();
        initGetAct();
    }

    private void getTagData() {

    }

    public void initGetAct() {

        new MyStringRequest(Urls.ACTIVITY_QUERY_MUTI, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                List<ActInfo> infos = JSONHandler.json2ListAct(json);

//                for (int i = 0; i <3 ; i++) {
//                    tagViews[i].initView();
//                    tagViews[i].setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            ((BaseActivity)getActivity()).toast(rootView,v.getId()+"");
//                        }
//                    });
//                }
//
//
                List<ActInfo> data1 = infos.subList(1, 4);
                List<ActInfo> data2 = infos.subList(3, 6);
                List<ActInfo> data3 = infos.subList(6, 9);
                List<ActInfo> data4 = infos.subList(1, 4);



                MyTagEntity tagEntitiy = new MyTagEntity();
                tagEntitiy.setActInfos(data1);
                MyItemOnClickListener listener=new MyItemOnClickListener(tagEntitiy);
                tagViews[0].setTag("猜你喜欢");
                tagViews[0].setActDate(tagEntitiy);
                tagViews[0].setListener(listener);


                MyTagEntity tagEntitiy1 = new MyTagEntity();
                tagEntitiy1.setActInfos(data2);
                MyItemOnClickListener listener1=new MyItemOnClickListener(tagEntitiy1);
                tagViews[1].setTag("运动");
                tagViews[1].setActDate(tagEntitiy1);
                tagViews[1].setOnClickListener(listener1);

                MyTagEntity tagEntitiy2 = new MyTagEntity();
                tagEntitiy2.setActInfos(data3);
                MyItemOnClickListener listener2=new MyItemOnClickListener(tagEntitiy2);
                tagViews[2].setTag("学习");
                tagViews[2].setActDate(tagEntitiy2);
                tagViews[2].setOnClickListener(listener2);


//
//
//                TagAdapter adapter = new TagAdapter(getActivity(), tags);
                //OfficeAdapter adapter = new OfficeAdapter(getActivity(), infos);
                //recyclerView.setAdapter(adapter);


//
//
//                tagViews[0].setTag("猜你喜欢");
//                tagViews[1].setTag("科技");
//                tagViews[2].setTag("运动");
//
//                tagViews[0].setActDate(data1);
//                tagViews[1].setActDate(data2);
//                tagViews[2].setActDate(data3);
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
                return null;
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


    public class MyItemOnClickListener implements View.OnClickListener{

        private MyTagEntity tag;
        public MyItemOnClickListener(MyTagEntity tag) {
            this.tag=tag;
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(getActivity(),AdDetailActivity.class);
            switch (v.getId()){
                case R.id.act_img1:
                    intent.putExtra("activity",tag.getActInfos().get(0));
                    startActivity(intent);
                    break;
                case R.id.act_img2:
                    intent.putExtra("activity",tag.getActInfos().get(1));
                    startActivity(intent);
                    break;
                case R.id.act_img3:
                    intent.putExtra("activity",tag.getActInfos().get(2));
                    startActivity(intent);
                    break;
                case R.id.more_tv:
                    break;
            }
        }
    }
}
