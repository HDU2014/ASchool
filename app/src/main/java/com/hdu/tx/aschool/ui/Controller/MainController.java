package com.hdu.tx.aschool.ui.Controller;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.ui.View.MainView;
import com.hdu.tx.aschool.ui.activity.LoginActivity;
import com.hdu.tx.aschool.ui.activity.MainActivity;
import com.hdu.tx.aschool.ui.activity.MeActivity;
import com.hdu.tx.aschool.ui.activity.MyInfoActivity;
import com.hdu.tx.aschool.ui.activity.UploadButtonDemoActivity;
import com.hdu.tx.aschool.ui.adapter.FragmentAdapter;
import com.hdu.tx.aschool.ui.fragment.ListFragment;
import com.hdu.tx.aschool.ui.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/21.
 */
public class MainController implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private MainActivity mainActivity;
    private MainView mainView;
    private List<Fragment> fragments;

    public MainController(MainActivity mainActivity, MainView mainView) {
        this.mainActivity=mainActivity;
        this.mainView=mainView;
        mainView.setListener(this);
        mainView.setNavigationMenuSelectedListener(this);


        List<String> titles = new ArrayList<>();
        titles.add("官方活动");
        titles.add("个人活动");
        titles.add("我的消息");
        fragments= new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        mainView.setViewPage(fragments,titles);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.headimg_iv:
                Intent intent=new Intent(mainActivity,MeActivity.class);
                mainActivity.startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mainView.closeDrawers();
        switch (menuItem.getItemId()){
            case R.id.my_info:
                Intent intent=new Intent(mainActivity,MyInfoActivity.class);
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
