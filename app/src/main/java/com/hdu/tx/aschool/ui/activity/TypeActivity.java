package com.hdu.tx.aschool.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.ui.adapter.FragmentAdapter;
import com.hdu.tx.aschool.ui.fragment.TypeFragment;
import com.hdu.tx.aschool.ui.widget.pupuwindow.DropdownButton;
import com.hdu.tx.aschool.ui.widget.pupuwindow.SelectItemsPop1;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenglin on 2015/9/1.
 */
public class TypeActivity extends BaseActivity implements SearchView.OnQueryTextListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.viewpager)
    ViewPager viewpager;


    private String[] types;
    private DropdownButton chooseSchool,chooseType,chooseTime;
    private View mask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        ButterKnife.bind(this);
        types=getResources().getStringArray(R.array.activity_type);
        initTabLayout();
        initChooseView();
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabs.setVisibility(View.VISIBLE);
                tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        toolbar.setTitle(tab.getText());
                        tabs.setVisibility(View.GONE);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        tabs.setVisibility(View.GONE);
                    }
                });
            }
        });

    }


    private void initChooseView() {

        chooseSchool = (DropdownButton) findViewById(R.id.chooseSchool);
        chooseType = (DropdownButton) findViewById(R.id.chooseType);
        chooseTime = (DropdownButton) findViewById(R.id.chooseTime);
        mask =  findViewById(R.id.mask);

        chooseSchool.setText("学校");
        chooseType.setText("类型");
        chooseTime.setText("时间");
        final String[] schoolsData = getResources().getStringArray(R.array.activity_schools);
        final SelectItemsPop1 popSchool = new SelectItemsPop1(TypeActivity.this, schoolsData, chooseSchool,mask);
        final String[] timeData = getResources().getStringArray(R.array.activity_time);
        final SelectItemsPop1 popTime = new SelectItemsPop1(TypeActivity.this, timeData, chooseTime,mask);
        final String[] typeData = getResources().getStringArray(R.array.activity_type);
        final SelectItemsPop1 popType = new SelectItemsPop1(TypeActivity.this, typeData, chooseType,mask);

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

    private void initTabLayout() {
        final List<Fragment> typeFragments=new ArrayList<>();
        final List<String> typeList=new ArrayList<>();

        for (int i = 0; i <types.length ; i++) {
//            TypeFragment fragment=new TypeFragment();
//            Bundle b=new Bundle();
//            b.putString("tag",types[i]);
//            fragment.setArguments(b);
//            fragment.setType(types[i]);
//            typeFragments.add(fragment);
//            typeList.add(types[i]);
        }
       // FragmentAdapter adapter =new FragmentAdapter(getSupportFragmentManager(), typeFragments, typeList);
       // viewpager.setAdapter(adapter);
        for (int i = 0; i <types.length ; i++) {
            tabs.addTab(tabs.newTab().setTag(types[i]));
        }
//        tabs.setupWithViewPager(viewpager);
       // tabs.setTabsFromPagerAdapter(adapter);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.typeactivity,menu);
        MenuItem item = menu.add("Search");
        item.setIcon(android.R.drawable.ic_menu_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        SearchView sv = new SearchView(this);
        sv.setOnQueryTextListener(this);
        item.setActionView(sv);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
