package com.hdu.tx.aschool.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.entity.InterestTabsEntitiy;
import com.hdu.tx.aschool.ui.IEvent.OnRecyleViewItemListner;
import com.hdu.tx.aschool.ui.adapter.InterestAdapter;
import com.hdu.tx.aschool.ui.adapter.InterestTabsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pualgo on 2015/8/31.
 */
public class InterestActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    String[] Data;
    String[] Data1;
    InterestAdapter interestAdapter;
    @Bind(R.id.interest_finish)
    TextView interestFinish;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private InterestTabsAdapter adapter;
    List<InterestTabsEntitiy> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interest);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initData();
    }

    private void initData() {
        RecyclerView.LayoutManager layout=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layout);

        try {
            adapter = new InterestTabsAdapter(this, getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(adapter);
        adapter.setOnItemsClickListner(new OnRecyleViewItemListner() {

            @Override
            public void onClick(View view, String tab) {

            }
        });
    }


    /**
     * 初始化标签数据
     * @return
     * @throws Exception
     */
    public List<InterestTabsEntitiy> getData() throws Exception{
        final String[] tabs = getResources().getStringArray(R.array.activity_type);
        list=new ArrayList<>();
        for (int i = 0; i < tabs.length; i++) {
            InterestTabsEntitiy tab=new InterestTabsEntitiy(tabs[i]);
            list.add(tab);
        }

        String s=MyApplication.getInstance().getUserInfo().getInterestTabs();
        String[] tabIndexs=new String[tabs.length];
        // String s="1,1,1,0,0,0,1,1,1";
        int[] indexs=new int[tabs.length];
        if(s!=null&&s.contains(",")){
            tabIndexs=s.split(",");
            for (int i = 0; i <tabIndexs.length ; i++) {
                indexs[i]= Integer.parseInt(tabIndexs[i]);
                list.get(i).setIsSelected(Integer.valueOf(tabIndexs[i])==0?false:true);
            }
        }
        return list;
    }

    @OnClick(R.id.interest_finish)
    void InterestFinish() {

    }


    public void saveData(){
        final String s =adapter.getTabsIndex();
        UserInfo user=MyApplication.getInstance().getUserInfo();
        user.setInterestTabs(s);
        MyApplication.getInstance().getDaoSession().update(user);
        finish();
    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("是否需要保存当前设置的兴趣标签？").setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveData();
            }
        }).setNegativeButton("放弃", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).create().show();
    }



}
