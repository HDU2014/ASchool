package com.hdu.tx.aschool.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.entity.MyTagEntity;
import com.hdu.tx.aschool.ui.View.TagView;
import com.hdu.tx.aschool.ui.activity.AdDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TagAdapter{

    private Activity mContext;
    private  List<ActInfo> tagEntities;
    private TagView[] tagViews;
    private LinearLayout ll;

    public TagAdapter(Activity mContext, LinearLayout ll){
        this.mContext = mContext;
        this.ll=ll;
        initView();
    }


    public void initView(){
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tagViews=new TagView[4];
        for (int i = 0; i <4 ; i++) {
            tagViews[i]= (TagView) mContext.getLayoutInflater().inflate(R.layout.cardview_tag,null);
            tagViews[i].setLayoutParams(params);
            tagViews[i].initView();
            ll.addView(tagViews[i]);
        }
    }

    public void setData( List<ActInfo> tagEntities){
        this.tagEntities=tagEntities;
        initData();
    }

    public void initData(){
        List<ActInfo>[] list=new List[4];
        list[0]=tagEntities.subList(1, 4);
        list[1]=tagEntities.subList(4, 7);
        list[2]=tagEntities.subList(7, 10);
        list[3]=tagEntities.subList(10, 13);
        for (int i = 0; i <4 ; i++) {
            tagViews[i].setTag("动态添加" + i);
            tagViews[i].setActDate(list[i]);

        }
    }

    public class MyItemOnClickListener implements View.OnClickListener{

        private List<ActInfo> tag;
        public MyItemOnClickListener(List<ActInfo> tag) {
            this.tag=tag;
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(mContext,AdDetailActivity.class);
            switch (v.getId()){
                case R.id.act_img1:
                    intent.putExtra("activity", tag.get(0));
                    mContext.startActivity(intent);
                    break;
                case R.id.act_img2:
                    intent.putExtra("activity",tag.get(1));
                    mContext.startActivity(intent);
                    break;
                case R.id.act_img3:
                    intent.putExtra("activity",tag.get(2));
                    mContext.startActivity(intent);
                    break;
                case R.id.more_tv:
                    break;
            }
        }
    }
}
