package com.hdu.tx.aschool.ui.Controller;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.ui.View.MainView;
import com.hdu.tx.aschool.ui.activity.LoginActivity;
import com.hdu.tx.aschool.ui.activity.MainActivity;
import com.hdu.tx.aschool.ui.activity.MeActivity;
import com.hdu.tx.aschool.ui.activity.MyInfoActivity;
import com.hdu.tx.aschool.ui.activity.UploadButtonDemoActivity;
import com.hdu.tx.aschool.ui.adapter.FragmentAdapter;
import com.hdu.tx.aschool.ui.fragment.AdFragment;
import com.hdu.tx.aschool.ui.fragment.ConstractFragment;
import com.hdu.tx.aschool.ui.fragment.ConversationFragment;
import com.hdu.tx.aschool.ui.fragment.ListFragment;
import com.hdu.tx.aschool.ui.fragment.MainFragment;
import com.hdu.tx.aschool.ui.fragment.MeFragment;
import com.hdu.tx.aschool.ui.fragment.OfficeFragment;
import com.hdu.tx.aschool.ui.fragment.PersonalFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/21.
 */
public class MainController implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private MainActivity mainActivity;
    private MainView mainView;
    private List<Fragment> fragments;
    private int fragmentIndex;

    public MainController(MainActivity mainActivity, MainView mainView) {
        this.mainActivity=mainActivity;
        this.mainView=mainView;
        mainView.setListener(this);
        mainView.setNavigationMenuSelectedListener(this);



        List<String> titles = new ArrayList<>();
        titles.add("推荐");
        titles.add("活动");
        titles.add("活动");
        titles.add("消息");
        titles.add("消息");
        titles.add("我的");
        fragments= new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new OfficeFragment());
        fragments.add(new PersonalFragment());
        fragments.add(new ConversationFragment());
        fragments.add(new ConstractFragment());
        fragments.add(new MeFragment());
        mainView.setViewPage(fragments, titles);
        mainView.setBottomIvSelect(0);
    }


    @Override
    public void onClick(View v) {
        int index=fragmentIndex;
        switch (v.getId()){
            case R.id.headimg_iv:
                mainView.closeDrawers();
                Intent intent=MyApplication.getInstance().getUserInfo().getLevel()==0?
                        new Intent(mainActivity,LoginActivity.class):
                        new Intent(mainActivity,MyInfoActivity.class);
                mainActivity.startActivity(intent);
                break;
            case R.id.bottom_iv0:
                index=0;
                break;
            case R.id.bottom_iv1:
                index=1;
                break;
            case R.id.bottom_iv2:
                index=3;
                break;
            case R.id.bottom_iv3:
                index=5;
                break;
            case R.id.bottom_iv4:
                break;
        }
        if(index!=fragmentIndex){
            mainView.setBottomIvSelect(index);
        }
        fragmentIndex=index;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mainView.closeDrawers();
        switch (menuItem.getItemId()){
            case R.id.my_info:
                Intent intent=new Intent(mainActivity,MeActivity.class);
                mainActivity.startActivity(intent);
                break;
            case R.id.my_activity:
                Intent intent1=new Intent(mainActivity,UploadButtonDemoActivity.class);
                mainActivity.startActivity(intent1);
                break;
            case R.id.my_messages:
                Intent intent2=new Intent(mainActivity,LoginActivity.class);
                mainActivity.startActivity(intent2);
                break;
            case R.id.exit:
                mainView.showExitAppDialog();
                break;
        }
        return true;
    }
}
