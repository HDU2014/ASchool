package com.hdu.tx.aschool.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.common.view.EditActivity;
import com.hdu.tx.aschool.ui.widget.image.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/8/14.
 */
public class MyInfoActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.head_img)
    CircleImageView headImg;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.nickname_ll)
    LinearLayout nicknameLl;
    @Bind(R.id.sex_ll)
    LinearLayout sexll;
    @Bind(R.id.sex_tv)
    TextView sextv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
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

        nicknameLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, EditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", "修改昵称");
                bundle.putString("descript", "修改昵称让好友们都记住你吧！");
                bundle.putString("hint", "输入你的昵称");
                intent.putExtras(bundle);
                MyInfoActivity.this.startActivity(intent);
            }
        });

        sexll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyInfoActivity.this);
                builder.setTitle("请选择性别");
                final String[] sex = {"男", "女"};
                builder.setItems(sex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sextv.setText(sex[which]);
                    }
                });
                builder.show();
            }
        });



    }
}
