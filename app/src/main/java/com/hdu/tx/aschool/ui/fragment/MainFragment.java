package com.hdu.tx.aschool.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.BaseFragment;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.JSONHandler;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.net.Urls;
import com.hdu.tx.aschool.ui.IEvent.LunBoOnClickListener;
import com.hdu.tx.aschool.ui.View.TagView;
import com.hdu.tx.aschool.ui.activity.AdDetailActivity;
import com.hdu.tx.aschool.ui.activity.AdDomain;
import com.hdu.tx.aschool.ui.activity.MainActivity;
import com.hdu.tx.aschool.ui.adapter.LunBoAdapter;
import com.hdu.tx.aschool.ui.adapter.OfficeAdapter;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/8/18.
 */
public class MainFragment extends BaseFragment {



    @Bind(R.id.v_dot0)
    View dot0;
    @Bind(R.id.v_dot1)
    View dot1;
    @Bind(R.id.v_dot2)
    View dot2;
    @Bind(R.id.v_dot3)
    View dot3;
    @Bind(R.id.v_dot4)
    View dot4;
    @Bind(R.id.lunboll)
    LinearLayout lunboll;

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

    public void initGetAct(){
        Map<String,String> map=new HashMap<>();
        map.put("last_aid","-1");
        map.put("act_num", "15");
        new MyStringRequest(Urls.GET_ACTIVITYS, map, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                try {
                    List<ActInfo> infos= JSONHandler.json2ListAct(json);
                    for (int i = 0; i <3 ; i++) {
                        tagViews[i].initView();
                        tagViews[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ((BaseActivity)getActivity()).toast(rootView,v.getId()+"");
                            }
                        });
                    }


                    List<ActInfo> data1=infos.subList(1,4);
                    List<ActInfo> data2=infos.subList(4,7);
                    List<ActInfo> data3=infos.subList(8,11);


                   tagViews[0].setTag("猜你喜欢");
                   tagViews[1].setTag("科技");
                   tagViews[2].setTag("运动");

                    tagViews[0].setActDate(data1);
                    tagViews[1].setActDate(data2);
                    tagViews[2].setActDate(data3);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String desc) {

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void getLunBoAct() {
        Map<String, String> map = new HashMap<>();
        new MyStringRequest(Urls.LUNBO_ACT, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                try {
                    initLunBoView(JSONHandler.json2ListAct(json));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("tag",e.toString());
                }
            }
            @Override
            public void error(String desc) {
                Log.i("TAG",desc);
            }
        });
    }

    public void initLunBoView(List<ActInfo> actInfos) {
        LunBoAdapter adapter = new LunBoAdapter((MainActivity) getActivity(),lunboll ,actInfos);
        adapter.setLunBoOnClickListener(new LunBoOnClickListener() {
            @Override
            public void onClick(ActInfo actInfo) {
                getActivity().startActivity(new Intent(getActivity(),AdDetailActivity.class).putExtra("activity",actInfo));
            }
        });
    }

}
