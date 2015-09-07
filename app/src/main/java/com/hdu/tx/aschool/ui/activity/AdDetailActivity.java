package com.hdu.tx.aschool.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
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
            Picasso.with(this).load(actInfo.getImageUrl()).into(hostHeadPic);
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


    @OnClick(R.id.hostname_tv)
    void getDetail() {
        Intent intent = new Intent(AdDetailActivity.this, OtherInfoActivity.class).putExtra("host_id", actInfo.getHostId());
        AdDetailActivity.this.startActivity(intent);
    }


    @OnClick(R.id.join)
    void onclick() {
        if (actInfo.getIsJoin()) {
            new MyStringRequest(Urls.ACTIVITY_JOIN_IN_CANCLE, new InternetListener() {
                @Override
                public void success(JSONObject json) {
                    //refreshJoinTv(false);
                    actInfo.setIsJoin(false);
                    actInfo.setJoinedpeopel(actInfo.getJoinedpeopel() - 1);
                    init(actInfo);

                }

                @Override
                public void error(String desc) {
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
            new MyStringRequest(Urls.ACTIVITY_JOIN_IN, new InternetListener() {
                @Override
                public void success(JSONObject json) {
                    //refreshJoinTv(true);
                    actInfo.setIsJoin(true);
                    actInfo.setJoinedpeopel(actInfo.getJoinedpeopel() + 1);
                    init(actInfo);
                }

                @Override
                public void error(String desc) {
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


    @OnClick(R.id.collect_ll)
    void onclick1() {

        if (actInfo.getIsCollect()) {
            new MyStringRequest(Urls.ACTIVITY_COLLECT_CANCLE, new InternetListener() {
                @Override
                public void success(JSONObject json) {
                    // refreshCollectTv(false);
                    actInfo.setIsCollect(false);
                    actInfo.setCollectTimes(actInfo.getCollectTimes() - 1);
                    init(actInfo);
                }

                @Override
                public void error(String desc) {
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
            new MyStringRequest(Urls.ACTIVITY_COLLECT, new InternetListener() {
                @Override
                public void success(JSONObject json) {
                    // refreshCollectTv(true);
                    actInfo.setIsCollect(true);
                    actInfo.setCollectTimes(actInfo.getCollectTimes() + 1);
                    init(actInfo);
                }

                @Override
                public void error(String desc) {
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


    public void refreshJoinTv(boolean flag) {
        actInfo.setIsJoin(flag);
        join.setSelected(flag);
        join.setText(flag ? "取消报名" : "去报名");

    }

    public void refreshCollectTv(boolean flag) {
        actInfo.setIsCollect(flag);
        collect.setSelected(flag);
        if (flag) {
            myCollectTv.setText("已收藏");
            myCollectTv.setTextColor(getResources().getColor(R.color.colorAccent));
            collectTv.setText(actInfo.getCollectTimes() + 1 + "");
        } else {
            myCollectTv.setText("收藏");
            myCollectTv.setTextColor(getResources().getColor(R.color.textcolor));
        }
    }


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


    @OnClick(R.id.group_chat)
    void onclick2() {
        String username = actInfo.getGroup_id();
        // 进入聊天页面
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("chatType", ChatActivity.CHATTYPE_GROUP);
        intent.putExtra("groupId", username);
        startActivity(intent);
    }


    public void setGroupMembers() {
        new MyStringRequest(Urls.USER_GROUP_MEMBERS, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                List<UserInfo> userInfos = JSONHandler.json2ListUser(json);
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


    public List<UserInfo> getDate() {
        List<UserInfo> userInfos=new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            UserInfo user=new UserInfo();
            user.setHeadimg_url(MyStrings.AVATARS[new Random().nextInt(MyStrings.AVATARS.length-1)]);
            userInfos.add(user);
        }
        return userInfos;
    }
}
