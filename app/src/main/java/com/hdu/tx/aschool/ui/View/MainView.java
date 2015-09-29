package com.hdu.tx.aschool.ui.View;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.ui.activity.LoginActivity;
import com.hdu.tx.aschool.ui.activity.MainActivity;
import com.hdu.tx.aschool.ui.activity.RegistActivity;
import com.hdu.tx.aschool.ui.adapter.FragmentAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.OnClick;

/**
 * Created by Administrator on 2015/8/21.
 */
public class MainView extends DrawerLayout {

    private Toolbar toolbar;
    private MainActivity mainActivity;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private ImageView headimg;
    private ViewPager viewPage;
    private TabLayout tabLayout;
    private TextView nickname;

    private TextView left_tv,right_tv;
    private ImageView left_iv,right_iv;
    private LinearLayout toolbar_ll;
    private int currentFragment;

    private ImageView[] bottom_iv=new ImageView[5];

    public MainView(Context context) {
        super(context);
    }


    public MainView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initView(final MainActivity mainActivity) {
        this.mainActivity=mainActivity;
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        mainActivity.setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(mainActivity, this, R.string.hello_world, R.string.hello_world){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                refreshMyInfo();
            }
        };
        this.setDrawerListener(drawerToggle);
        mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainView.this.openDrawer(Gravity.LEFT);
            }
        });
        drawerToggle.syncState();
        //tabLayout = (TabLayout) findViewById(R.id.tabs);
        navigationView = (NavigationView) findViewById(R.id.nv_main_navigation);
        headimg=(ImageView) navigationView.findViewById(R.id.headimg_iv);
        nickname= (TextView) navigationView.findViewById(R.id.nickname);
        toolbar_ll= (LinearLayout) findViewById(R.id.fragment1);
        left_iv= (ImageView) findViewById(R.id.left_iv);
        right_iv= (ImageView) findViewById(R.id.right_iv);
        left_tv= (TextView) findViewById(R.id.left_tv);
        right_tv= (TextView) findViewById(R.id.right_tv);
        bottom_iv[0]= (ImageView) findViewById(R.id.bottom_iv0);
        bottom_iv[1]= (ImageView) findViewById(R.id.bottom_iv1);
        bottom_iv[2]= (ImageView) findViewById(R.id.bottom_iv2);
        bottom_iv[3]= (ImageView) findViewById(R.id.bottom_iv3);
        bottom_iv[4]= (ImageView) findViewById(R.id.bottom_iv4);




        viewPage= (ViewPager) findViewById(R.id.viewpager);
        if(MyApplication.getInstance().getUserInfo().getLevel()==0){
            Snackbar.make(toolbar,R.string.need_login,Snackbar.LENGTH_LONG).setAction(R.string.now_regist, new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mainActivity, RegistActivity.class);
                    mainActivity.startActivity(intent);
                }
            }).show();
        }
        refreshMyInfo();
    }

    public void setNavigationMenuSelectedListener(
            NavigationView.OnNavigationItemSelectedListener
                    navigationMenuSelectedListener) {
         if(navigationView!=null)
             navigationView.setNavigationItemSelectedListener(navigationMenuSelectedListener);
    }



    public void setListener(View.OnClickListener listener) {
        headimg.setOnClickListener(listener);
        for (int i = 0; i < 5; i++) {
            bottom_iv[i].setOnClickListener(listener);
        }
        left_tv.setOnClickListener(listener);
        right_tv.setOnClickListener(listener);
    }


    public void setBottomIvSelect(int index){
        currentFragment=index;
        viewPage.setCurrentItem(index);
        toolbar_ll.setVisibility(VISIBLE);
        switch (index){
            case 0:
                toolbar_ll.setVisibility(GONE);
                toolbar.setTitle(R.string.tuijie);
                toolbar.setSubtitle("");
                setSelectIv(0);
                break;
            case 1:

                toolbar.setTitle(R.string.person);
                toolbar.setSubtitle(R.string.office);
                left_tv.setText(R.string.office);
                right_tv.setText(R.string.person);
                left_iv.setVisibility(VISIBLE);
                right_iv.setVisibility(INVISIBLE);
                setSelectIv(1);
                break;
            case 2:
                toolbar.setTitle(R.string.office);
                toolbar.setSubtitle(R.string.person);
                left_tv.setText(R.string.office);
                right_tv.setText(R.string.person);
                left_iv.setVisibility(INVISIBLE);
                right_iv.setVisibility(VISIBLE);
                setSelectIv(1);
                break;
            case 3:
                toolbar.setTitle(R.string.huihua);
                toolbar.setSubtitle(R.string.contract);
                left_tv.setText(R.string.huihua);
                right_tv.setText(R.string.friend);
                left_iv.setVisibility(VISIBLE);
                right_iv.setVisibility(INVISIBLE);
                setSelectIv(2);
                break;
            case 4:
                toolbar.setTitle(R.string.contract);
                toolbar.setSubtitle(R.string.huihua);
                left_tv.setText(R.string.huihua);
                right_tv.setText(R.string.friend);
                left_iv.setVisibility(INVISIBLE);
                right_iv.setVisibility(VISIBLE);
                setSelectIv(2);
                break;
            case 5:
                toolbar_ll.setVisibility(GONE);
                toolbar.setTitle(R.string.mine);
                toolbar.setSubtitle("");
                setSelectIv(3);
                break;
        }

    }


    public void setSelectIv(int index){
        for (int i = 0; i <4 ; i++) {
            if(i==index){
                bottom_iv[i].setSelected(true);
            }else{
                bottom_iv[i].setSelected(false);
            }
        }
    }


    public void setViewPage(List<Fragment> fragments,List<String> titles){
        FragmentAdapter adapter =
                new FragmentAdapter(mainActivity.getSupportFragmentManager(), fragments, titles);
        viewPage.setAdapter(adapter);

//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));
//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(3)));
//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(4)));
//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(5)));
//        tabLayout.setupWithViewPager(viewPage);
//        tabLayout.setTabsFromPagerAdapter(adapter);
        viewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setBottomIvSelect(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }


    public void showChanggeAccountDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(mainActivity);
        builder.setTitle(R.string.change_account).setMessage(R.string.change_account_message)
                .setNegativeButton("取消",null).setNeutralButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainActivity.onBackPressed();
            }
        }).setPositiveButton("更换账号", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserInfo userInfo=new UserInfo();
                userInfo.setId(1l);
                userInfo.setLevel(0);
                userInfo.setLoadTimes(0);
                userInfo.setUsername("游客");
                MyApplication.getInstance().getDaoSession().deleteAll(UserInfo.class);
                MyApplication.getInstance().getDaoSession().insert(userInfo);
                MyApplication.getInstance().setUserInfo(userInfo);
                mainActivity.startActivity(new Intent(mainActivity, LoginActivity.class));
                mainActivity.finish();
            }
        }).create().show();
    }

    public void refreshMyInfo(){
        UserInfo info=MyApplication.getInstance().getUserInfo();
        if(info.getLevel()==1){
            Picasso.with(mainActivity).load(MyApplication.getInstance().getUserInfo().getHeadimg_url()).into(headimg);
            nickname.setText(info.getNickname());
        }
    }

    public int getCurrentFragement(){
        return this.currentFragment;
    }
}
