package com.hdu.tx.aschool.ui.Controller;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.common.utils.ConstantValue;
import com.hdu.tx.aschool.ui.View.MainView;
import com.hdu.tx.aschool.ui.activity.AboutActivity;
import com.hdu.tx.aschool.ui.activity.InterestActivity;
import com.hdu.tx.aschool.ui.activity.LoginActivity;
import com.hdu.tx.aschool.ui.activity.MainActivity;
import com.hdu.tx.aschool.ui.activity.MyInfoActivity;
import com.hdu.tx.aschool.ui.activity.PublishActivity;
import com.hdu.tx.aschool.ui.fragment.ChatAllHistoryFragment;
import com.hdu.tx.aschool.ui.fragment.ContactlistFragment;
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
    private static final String TAG =MainController.class.getSimpleName() ;
    private MainActivity mainActivity;
    private MainView mainView;
    private List<Fragment> fragments;
    private int index;

    public MainController(MainActivity mainActivity, MainView mainView) {
        this.mainActivity=mainActivity;
        this.mainView=mainView;
        mainView.setListener(this);
        mainView.setNavigationMenuSelectedListener(this);
        initFragment();
    }

    private void initFragment() {
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
        fragments.add(new ChatAllHistoryFragment());
        fragments.add(new ContactlistFragment());
        fragments.add(new MeFragment());
        mainView.setViewPage(fragments, titles);
        mainView.setBottomIvSelect(0);
    }


    public void setChatController(ChatController chatController){
        ((ChatAllHistoryFragment)fragments.get(3)).setChatController(chatController);
        ((ContactlistFragment)fragments.get(4)).setChatController(chatController);
    }



    @Override
    public void onClick(View v) {
        index=mainView.getCurrentFragement();
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
                Intent intent1=new Intent(mainActivity, PublishActivity.class);
                mainActivity.startActivity(intent1);
                break;

            case R.id.left_tv:
                Log.i(TAG,"left_tv--->index="+index);
                if(index==2)index=1;
                if(index==4)index=3;
                break;
            case R.id.right_tv:
                Log.i(TAG,"right_tv--->index="+index);
                if(index==1)index=2;
                if(index==3)index=4;
                break;
        }
        if(index!=mainView.getCurrentFragement()){
            mainView.setBottomIvSelect(index);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mainView.closeDrawers();
        switch (menuItem.getItemId()){
//            case R.id.my_activity:
//                Intent intent1=new Intent(mainActivity,UploadButtonDemoActivity.class);
//                mainActivity.startActivity(intent1);
//                break;
            case R.id.menu_interest:
                Intent intent1=new Intent(mainActivity,InterestActivity.class);
                mainActivity.startActivity(intent1);
                break;
            case R.id.exit:
               // mainView.showExitAppDialog();
                mainView.showChanggeAccountDialog();
                break;
            case R.id.menu_settings:

                break;
            case R.id.menu_about:
                Intent intent2=new Intent(mainActivity,AboutActivity.class);
                mainActivity.startActivity(intent2);
                break;

        }
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== ConstantValue.RESULT_CANCLE)return;
        if(requestCode==ConstantValue.INTENT_ADDETAIL){
            if(mainView.getCurrentFragement()==1){
                OfficeFragment fragment=(OfficeFragment)fragments.get(1);
                if(fragment!=null&&data!=null)fragment.refresh(data);
            }
        }
    }
}
