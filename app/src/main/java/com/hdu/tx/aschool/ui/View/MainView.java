package com.hdu.tx.aschool.ui.View;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.ui.Controller.MainController;
import com.hdu.tx.aschool.ui.activity.MainActivity;
import com.hdu.tx.aschool.ui.activity.MeActivity;
import com.hdu.tx.aschool.ui.activity.RegistActivity;
import com.hdu.tx.aschool.ui.adapter.FragmentAdapter;

import java.util.List;

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
        drawerToggle = new ActionBarDrawerToggle(mainActivity, this, R.string.hello_world, R.string.hello_world);
        this.setDrawerListener(drawerToggle);
        mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nv_main_navigation);
        headimg=(ImageView) navigationView.findViewById(R.id.headimg_iv);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPage= (ViewPager) findViewById(R.id.viewpager);
        if(MyApplication.getInstance().getUserInfo().getLevel()==0){
            Snackbar.make(toolbar,R.string.need_login,Snackbar.LENGTH_SHORT).setAction(R.string.now_regist, new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mainActivity, RegistActivity.class);
                    mainActivity.startActivity(intent);
                }
            }).show();
        }
    }

    public void setNavigationMenuSelectedListener(
            NavigationView.OnNavigationItemSelectedListener
                    navigationMenuSelectedListener) {
         if(navigationView!=null)
             navigationView.setNavigationItemSelectedListener(navigationMenuSelectedListener);
    }



    public void setListener(View.OnClickListener listener) {
        headimg.setOnClickListener(listener);
    }

    public void setViewPage(List<Fragment> fragments,List<String> titles){
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));
        FragmentAdapter adapter =
                new FragmentAdapter(mainActivity.getSupportFragmentManager(), fragments, titles);
        viewPage.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPage);
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    public void showExitAppDialog(){
        final NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(mainActivity);
        dialogBuilder
                .withTitle("退出")
                .withTitleColor("#ffffffff")
                .withDividerColor("#11000000")
                .withMessage("确定退出程序？")
                .withMessageColor("#FFFFFFFF")
                .withDialogColor(mainActivity.getResources().getColor(R.color.colorPrimary))
                .withIcon(mainActivity.getResources().getDrawable(R.mipmap.ic_launcher))
                .withDuration(300)
                .withEffect(Effectstype.RotateBottom)
                .withButton1Text("确定")
                .withButton2Text("取消")
                .isCancelableOnTouchOutside(true)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainActivity.onBackPressed();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                    }
                })
                .show();
    }
}
