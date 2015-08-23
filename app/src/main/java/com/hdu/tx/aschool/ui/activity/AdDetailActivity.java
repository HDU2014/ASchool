package com.hdu.tx.aschool.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/8/21.
 */
public class AdDetailActivity extends BaseActivity {


    @Bind(R.id.backdrop)
    ImageView backdrop;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.look)
    TextView look;
    @Bind(R.id.nickname_ll)
    LinearLayout nicknameLl;
    @Bind(R.id.lunboll)
    LinearLayout lunboll;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adetail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }


}
