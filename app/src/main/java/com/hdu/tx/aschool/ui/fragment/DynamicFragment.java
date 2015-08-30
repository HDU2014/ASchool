package com.hdu.tx.aschool.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.BaseFragment;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.net.Urls;
import com.squareup.picasso.Picasso;

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
    private BaseActivity superActiviyt;
    public ActInfo actInfo;
    public List<ActInfo> actInfoList;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.superActiviyt=(BaseActivity)getActivity();
        actInfo = new ActInfo();
        actInfoList = new ArrayList<>();
        getActivtyData();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DynamicView = inflater.inflate(R.layout.dynamic_cardview, container, false);
        ButterKnife.bind(this, DynamicView);
        return DynamicView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    public void getActivtyData() {


        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.GET_ACTIVITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject object=new JSONObject(s);
                    if(object.getInt("result")==200){

//                        private Long id;
//                        private String hostname;
//                        private String imageUrl;
//                        private String title;
//                        private String time;
//                        private String address;
//                        private String describe;
//                        private Integer hostId;
//                        private Integer lookTimes;
//                        private Integer collectTimes;
//                        private Integer totalpeopel;
//                        private Integer joinedpeopel;


                        Picasso.with(superActiviyt).load(object.getString("act_img")).into(eventImage);
                        eventTitle.setText(object.getString("title"));
                        host.setText(object.getString("host_name"));
                        eventTime.setText(object.getString("start_time"));
                        eventAddress.setText(object.getString("act_place"));
                        eventCollect.setText(object.getString("collect_num"));


                    }else{
//                           toast(toolbar,object.getString("desc"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    // toast(toolbar,e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("act_id","00000001");
                return map;
            }
        };
        superActiviyt.getVolleyQueue().add(stringRequest);
    }
}
