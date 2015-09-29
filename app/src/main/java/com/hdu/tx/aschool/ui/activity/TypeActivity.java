package com.hdu.tx.aschool.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.ui.adapter.FragmentAdapter;
import com.hdu.tx.aschool.ui.fragment.TypeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenglin on 2015/9/1.
 */
public class TypeActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    private String[] types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        ButterKnife.bind(this);
        types=getResources().getStringArray(R.array.activity_type);
        initTabLayout();
    }


    private void initTabLayout() {
        final List<Fragment> typeFragments=new ArrayList<>();
        final List<String> typeList=new ArrayList<>();

        for (int i = 0; i <types.length ; i++) {
            TypeFragment fragment=new TypeFragment();
            Bundle b=new Bundle();
            b.putString("tag",types[i]);
            fragment.setArguments(b);
            fragment.setType(types[i]);
            typeFragments.add(fragment);
            typeList.add(types[i]);
        }
        FragmentAdapter adapter =new FragmentAdapter(getSupportFragmentManager(), typeFragments, typeList);
        viewpager.setAdapter(adapter);
        for (int i = 0; i <typeList.size() ; i++) {
            tabs.addTab(tabs.newTab().setTag(typeList.get(i)));
        }
        tabs.setupWithViewPager(viewpager);
        tabs.setTabsFromPagerAdapter(adapter);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                TypeFragment f= (TypeFragment) typeFragments.get(position);
//                f.setType(typeList.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
