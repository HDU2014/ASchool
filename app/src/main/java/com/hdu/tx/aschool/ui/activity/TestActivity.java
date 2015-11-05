package com.hdu.tx.aschool.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.common.utils.MyStrings;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.ui.View.HeadImageView;
import com.hdu.tx.aschool.ui.widget.pupuwindow.DropdownButton;
import com.hdu.tx.aschool.ui.widget.pupuwindow.SelectItemsPop;
import com.hdu.tx.aschool.ui.widget.pupuwindow.SelectItemsPop1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/8/15.
 */
public class TestActivity extends BaseActivity {

private DropdownButton chooseSchool,chooseType,chooseTime;
    private View mask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow1);
        chooseSchool = (DropdownButton) findViewById(R.id.chooseSchool);
        chooseType = (DropdownButton) findViewById(R.id.chooseType);
        chooseTime = (DropdownButton) findViewById(R.id.chooseTime);
        mask =  findViewById(R.id.mask);

        initChooseView();
    }

    private void initChooseView() {
        chooseSchool.setText("学校");
        chooseType.setText("类型");
        chooseTime.setText("时间");
        final String[] schoolsData = getResources().getStringArray(R.array.activity_schools);
        final SelectItemsPop1 popSchool = new SelectItemsPop1(TestActivity.this, schoolsData, chooseSchool,mask);
        final String[] timeData = getResources().getStringArray(R.array.activity_time);
        final SelectItemsPop1 popTime = new SelectItemsPop1(TestActivity.this, timeData, chooseTime,mask);
        final String[] typeData = getResources().getStringArray(R.array.activity_type);
        final SelectItemsPop1 popType = new SelectItemsPop1(TestActivity.this, typeData, chooseType,mask);

        popSchool.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chooseSchool.setText(schoolsData[position]);
                popSchool.dismiss();
            }
        });

        popTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chooseTime.setText(timeData[position]);
                popTime.dismiss();
            }
        });


        popType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chooseType.setText(typeData[position]);
                popType.dismiss();

            }
        });
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
    }


}
