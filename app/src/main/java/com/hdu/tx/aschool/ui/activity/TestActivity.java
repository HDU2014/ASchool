package com.hdu.tx.aschool.ui.activity;

import android.os.Bundle;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.common.utils.MyStrings;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.ui.View.HeadImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/8/15.
 */
public class TestActivity extends BaseActivity {

    @Bind(R.id.linear_layout)
    HeadImageView linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_members);
        ButterKnife.bind(this);

    }

    public List<UserInfo> getDate() {
        List<UserInfo> userInfos=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            UserInfo user=new UserInfo();
            user.setHeadimg_url(MyStrings.AVATARS[new Random().nextInt(MyStrings.AVATARS.length-1)]);
            userInfos.add(user);
        }
        return userInfos;
    }


    @Override
    protected void onResume() {
        super.onResume();
        linearLayout.setDate(getDate());
    }
}
