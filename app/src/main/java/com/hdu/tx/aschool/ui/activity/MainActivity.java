package com.hdu.tx.aschool.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.common.view.MyNiftyDialog;
import com.hdu.tx.aschool.ui.adapter.FragmentAdapter;
import com.hdu.tx.aschool.ui.fragment.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    ActionBarDrawerToggle drawerToggle;
    FloatingActionButton fab;
    private ImageView headimg_iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main_drawer);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, R.string.hello_world, R.string.hello_world);
        mDrawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();

        NavigationView navigationView =
                (NavigationView) findViewById(R.id.nv_main_navigation);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
            headimg_iv= (ImageView) navigationView.findViewById(R.id.headimg_iv);
            headimg_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,MeActivity.class);
                    MainActivity.this.startActivity(intent);
                }
            });
        }



        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.need_login, Snackbar.LENGTH_LONG)
                        .setAction(R.string.now_regist, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                                MainActivity.this.startActivity(intent);

                            }
                        }).show();
            }
        });

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        List<String> titles = new ArrayList<>();
        titles.add("官方活动");
        titles.add("个人活动");
        titles.add("我的消息");
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        FragmentAdapter adapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        switch (menuItem.getItemId()){
                            case R.id.my_info:
                                Intent intent=new Intent(MainActivity.this,MyInfoActivity.class);
                                MainActivity.this.startActivity(intent);
                                break;
                            case R.id.my_activity:
                                Intent intent1=new Intent(MainActivity.this,UploadButtonDemoActivity.class);
                                MainActivity.this.startActivity(intent1);
                                break;
                            case R.id.my_messages:
                                Intent intent2=new Intent(MainActivity.this,LoginActivity.class);
                                MainActivity.this.startActivity(intent2);
                                break;
                            case R.id.exit:
                                final NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(MainActivity.this);
                                dialogBuilder
                                        .withTitle("退出")
                                        .withTitleColor("#ffffffff")
                                        .withDividerColor("#11000000")
                                        .withMessage("确定退出程序？")
                                        .withMessageColor("#FFFFFFFF")
                                        .withDialogColor(getResources().getColor(R.color.colorPrimary))
                                        .withIcon(getResources().getDrawable(R.drawable.ic_plus))
                                        .withDuration(300)
                                        .withEffect(Effectstype.SlideBottom)
                                        .withButton1Text("确定")
                                        .withButton2Text("取消")
                                        .isCancelableOnTouchOutside(true)
                                        .setButton1Click(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                onBackPressed();
                                            }
                                        })
                                        .setButton2Click(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialogBuilder.dismiss();
                                            }
                                        })
                                        .show();
                                break;
                        }
                        return true;
                    }
                });

    }




}
