package com.hdu.tx.aschool.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
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

        adapter = new InterestTabsAdapter(this, getData());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemsClickListner(new OnRecyleViewItemListner() {

            @Override
            public void onClick(View view, String tab) {
             toast(toolbar,tab);
            }
        });
    }


    public List<InterestTabsEntitiy> getData(){
        final String[] tabs = getResources().getStringArray(R.array.activity_type);
        list=new ArrayList<>();
        for (int i = 0; i < tabs.length; i++) {
            InterestTabsEntitiy tab=new InterestTabsEntitiy(tabs[i]);
            list.add(tab);
        }
        return list;
    }

    @OnClick(R.id.interest_finish)
    void InterestFinish() {

    }

}
