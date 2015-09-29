package com.hdu.tx.aschool.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.ui.adapter.InterestAdapter;

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
    @Bind(R.id.gridview)
    GridView gridview;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    String[] Data;
    String[] Data1;
    InterestAdapter interestAdapter;
    @Bind(R.id.interest_finish)
    TextView interestFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interest);
        Data1 = InterestActivity.this.getResources().getStringArray(R.array.activity_type);
        Data = new String[Data1.length - 2];
        reBuildData();
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
        setInterestImg();
        changeItemBackground();
    }

    public void reBuildData() {
        int dataSize = Data1.length;
        for (int i = 1; i < dataSize - 1; i++) {
            Data[i - 1] = Data1[i];
        }
    }

    public void setInterestImg() {
        interestAdapter = new InterestAdapter(InterestActivity.this, Data);
        gridview.setAdapter(interestAdapter);


    }

    public void changeItemBackground() {
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridview.setSelection(position);
                interestAdapter.setClickItem(position);
                interestAdapter.notifyDataSetChanged();
            }
        });
    }
    @OnClick(R.id.interest_finish)
    void InterestFinish()
    {

    }

}
