package com.hdu.tx.aschool.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.common.utils.MyStrings;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.JSONHandler;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.net.Urls;
import com.hdu.tx.aschool.ui.View.HeadImageView;
import com.hdu.tx.aschool.ui.widget.image.CircleImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/8/21.
 */
public class AdDetailActivity extends BaseActivity {


    @Bind(R.id.act_img)
    ImageView actImg;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.look_iv)
    ImageView lookIv;
    @Bind(R.id.look_tv)
    TextView lookTv;
    @Bind(R.id.collect_iv)
    ImageView collectIv;
    @Bind(R.id.collect_tv)
    TextView collectTv;
    @Bind(R.id.time_tv)
    TextView timeTv;
    @Bind(R.id.address_tv)
    TextView addressTv;
    @Bind(R.id.person_num)
    TextView personNum;
    @Bind(R.id.hostname_tv)
    TextView hostnameTv;
    @Bind(R.id.host_head_pic)
    CircleImageView hostHeadPic;
    @Bind(R.id.nickname_ll)
    LinearLayout nicknameLl;
    @Bind(R.id.headimages)
    HeadImageView headimages;
    @Bind(R.id.group_chat)
    Button groupChat;
    @Bind(R.id.describe_tv)
    TextView describeTv;
    @Bind(R.id.lunboll)
    LinearLayout lunboll;
    @Bind(R.id.nsv)
    NestedScrollView nsv;
    @Bind(R.id.collect)
    ImageView collect;
    @Bind(R.id.my_collect_tv)
    TextView myCollectTv;
    @Bind(R.id.collect_ll)
    LinearLayout collectLl;
    @Bind(R.id.join)
    TextView join;
    @Bind(R.id.bt)
    LinearLayout bt;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    private ActInfo actInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adetail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbar.setTitle("活动详情");
        actInfo = (ActInfo) getIntent().getSerializableExtra("activity");
        browse();
        //headimages.setDate(getDate());
       setGroupMembers();
        init(actInfo);
    }

    public void init(ActInfo actInfo) {

        if (actInfo == null) return;
        String path = actInfo.getImageUrl();
        if (path != null && !"".equals(path))
            Picasso.with(this).load(actInfo.getImageUrl()).into(actImg);
        String path1 = actInfo.getHostimageUrl();
        if (path != null && !"".equals(path))
            Picasso.with(this).load(actInfo.getHostimageUrl()).into(hostHeadPic);
        title.setText(actInfo.getTitle());
        timeTv.setText(actInfo.getTime());
        collectTv.setText(actInfo.getCollectTimes() + "");
        lookTv.setText(actInfo.getLookTimes() + "");
        addressTv.setText(actInfo.getAddress());
        personNum.setText(actInfo.getJoinedpeopel() + "/" + actInfo.getTotalpeopel());
        hostnameTv.setText(actInfo.getHostname());
        describeTv.setText(actInfo.getDescribe());
        join.setSelected(actInfo.getIsJoin());
        collectLl.setSelected(actInfo.getIsCollect());
        collect.setSelected(actInfo.getIsCollect());
        if (actInfo.getIsCollect()) {
            myCollectTv.setText("已收藏");
            myCollectTv.setTextColor(getResources().getColor(R.color.colorAccent));
        } else {
            myCollectTv.setText("收藏");
            myCollectTv.setTextColor(getResources().getColor(R.color.textcolor));
        }
        if (actInfo.getIsJoin()) {
            join.setText("取消报名");
        } else {
            join.setText("去报名");
        }

    }


    /**
     * 进入主办方详细介绍
     */
    @OnClick(R.id.hostname_tv)
    void getDetail() {
        Intent intent = new Intent(AdDetailActivity.this, OtherInfoActivity.class).putExtra("host_username", actInfo.getHost_username());
        AdDetailActivity.this.startActivity(intent);
    }


    /**
     * 参加活动点击事件
     */
    String  currentTime(){
        String strTime;
        Calendar calendar = Calendar.getInstance();
        strTime = calendar.get(Calendar.MONTH) + "-"+calendar.get(Calendar.DAY_OF_MONTH)+"  "+ calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
        Log.v("时间",strTime);

        return strTime;
    }
    @OnClick(R.id.join)
    void onclick() {
//        if(actInfo.getTime().compareTo(currentTime())<0)
//            toast(toolbar, "活动已过期！");
         if (actInfo.getIsJoin()) {
            showProgressDialog(this,R.string.canceling);
            new MyStringRequest(Urls.ACTIVITY_JOIN_IN_CANCLE, new InternetListener() {
                @Override
                public void success(JSONObject json) {
                    //refreshJoinTv(false);
                    dismissProgressDialog();
                    actInfo.setIsJoin(false);
                    actInfo.setJoinedpeopel(actInfo.getJoinedpeopel() - 1);
                    init(actInfo);

                }

                @Override
                public void error(String desc) {
                    dismissProgressDialog();
                    toast(toolbar, desc);
                }

                @Override
                public Map<String, String> setParams() {
                    Map<String, String> map = new HashMap<>();
                    map.put("user_name", MyApplication.getInstance().getUserInfo().getUsername());
                    map.put("act_id", actInfo.getActId());
                    return map;
                }
            });
        } else {
            showProgressDialog(this,R.string.joining_activity);
            new MyStringRequest(Urls.ACTIVITY_JOIN_IN, new InternetListener() {
                @Override
                public void success(JSONObject json) {
                    //refreshJoinTv(true);
                    dismissProgressDialog();
                    actInfo.setIsJoin(true);
                    actInfo.setJoinedpeopel(actInfo.getJoinedpeopel() + 1);
                    init(actInfo);
                }

                @Override
                public void error(String desc) {
                    dismissProgressDialog();
                    toast(toolbar, desc);
                }

                @Override
                public Map<String, String> setParams() {
                    Map<String, String> map = new HashMap<>();
                    map.put("user_name", MyApplication.getInstance().getUserInfo().getUsername());
                    map.put("act_id", actInfo.getActId());
                    return map;
                }
            });
        }
    }


    //收藏活动点击事件
    @OnClick(R.id.collect_ll)
    void onclick1() {
        if (actInfo.getIsCollect()) {
            showProgressDialog(this,R.string.canceling);
            new MyStringRequest(Urls.ACTIVITY_COLLECT_CANCLE, new InternetListener() {
                @Override
                public void success(JSONObject json) {
                    // refreshCollectTv(false);
                    dismissProgressDialog();
                    actInfo.setIsCollect(false);
                    actInfo.setCollectTimes(actInfo.getCollectTimes() - 1);
                    init(actInfo);
                }

                @Override
                public void error(String desc) {
                    dismissProgressDialog();
                    toast(toolbar, desc);
                }

                @Override
                public Map<String, String> setParams() {
                    Map<String, String> map = new HashMap<>();
                    map.put("user_name", MyApplication.getInstance().getUserInfo().getUsername());
                    map.put("act_id", actInfo.getActId());
                    return map;
                }
            });
        } else {
            showProgressDialog(this,R.string.submiting);
            new MyStringRequest(Urls.ACTIVITY_COLLECT, new InternetListener() {
                @Override
                public void success(JSONObject json) {
                    // refreshCollectTv(true);
                    dismissProgressDialog();
                    actInfo.setIsCollect(true);
                    actInfo.setCollectTimes(actInfo.getCollectTimes() + 1);
                    init(actInfo);
                }

                @Override
                public void error(String desc) {
                    dismissProgressDialog();
                    toast(toolbar, desc);
                }

                @Override
                public Map<String, String> setParams() {
                    Map<String, String> map = new HashMap<>();
                    map.put("user_name", MyApplication.getInstance().getUserInfo().getUsername());
                    map.put("act_id", actInfo.getActId());
                    return map;
                }
            });
        }
    }





    /**
     * 浏览活动的网络请求
     */
    public void browse() {
        new MyStringRequest(Urls.ACTIVITY_BROWSE, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                actInfo.setLookTimes(actInfo.getLookTimes() + 1);
                init(actInfo);
            }

            @Override
            public void error(String desc) {

            }

            @Override
            public Map<String, String> setParams() {
                Map<String, String> map = new HashMap<>();
                map.put("act_id", actInfo.getActId());
                return map;
            }
        });
    }


    /**
     * 群聊点击事件
     */
    @OnClick(R.id.group_chat)
    void onclick2() {
        if(!actInfo.getIsJoin()){
            Snackbar.make(toolbar,R.string.sorry_you_not_join,Snackbar.LENGTH_LONG).setAction(R.string.goto_join,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onclick();
                        }
                    }).show();
            return;
        }
        String username = actInfo.getGroup_id();
        // 进入聊天页面
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("chatType", ChatActivity.CHATTYPE_GROUP);
        intent.putExtra("groupId", username);
        startActivity(intent);
    }


    /**
     * 设置群聊成员的图像
     */
    public void setGroupMembers() {
        new MyStringRequest(Urls.USER_GROUP_MEMBERS, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                List<UserInfo> userInfos = JSONHandler.json2ListUser(json);
                if(userInfos.size()>1) {
                    for (int i = 0; i < userInfos.size(); i++)
                        if (userInfos.get(i).getNickname().equals(actInfo.getHostname())) {
                            userInfos.remove(i);
                        }
                }
                headimages.setDate(userInfos);
            }

            @Override
            public void error(String desc) {

            }

            @Override
            public Map<String, String> setParams() {
                Map<String, String> map = new HashMap<>();
                map.put("group_id", actInfo.getGroup_id());
                return map;
            }
        });
    }


}