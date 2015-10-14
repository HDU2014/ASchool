package com.hdu.tx.aschool.ui.View;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.ui.activity.AdDetailActivity;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2015/9/7.
 */
public class TagsView extends LinearLayout{
    private List<ActInfo> actInfos;
    private Context context;
    private TagView[] tagViews;
    private int size;
    private String[] types;

    public TagsView(Context context) {
        super(context);
        this.context=context;
    }

    public TagsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public TagsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    public void initView(){
            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            types=context.getResources().getStringArray(R.array.activity_type);
            int[] indexs=new int[types.length];
            String s= MyApplication.getInstance().getUserInfo().getInterestTabs();
            if(s!=null&&s.contains(",")){

            }
            size=types.length-1;
            for(int i = 0; i<types.length-1; i++)
            {
                types[i]=types[i+1];
            }
            tagViews=new TagView[size];
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            for (int i = 0; i <size ; i++) {
                tagViews[i]= (TagView) inflater.inflate(R.layout.cardview_tag,null);
                tagViews[i].setLayoutParams(params);
                tagViews[i].initView();
                this.addView(tagViews[i]);
            }

    }


    public void setDate(List<ActInfo> actInfoList){
        this.actInfos=actInfoList;
        for (int i = 0; i <size ; i++) {
            int start=new Random().nextInt(11);
            List<ActInfo> acts=actInfoList.subList(start,start+3);
            tagViews[i].setTag(types[i]);
            tagViews[i].setActDate(acts);
            tagViews[i].setListener(new MyItemOnClickListener(acts,types[i]));
        }
    }

    public class MyItemOnClickListener implements View.OnClickListener{

        private List<ActInfo> tag;
        private String type;
        public MyItemOnClickListener(List<ActInfo> tag,String type) {
            this.tag=tag;
            this.type=type;
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,AdDetailActivity.class);
            switch (v.getId()){
                case R.id.act_img1:
                    intent.putExtra("activity", tag.get(0));
                    context.startActivity(intent);
                    break;
                case R.id.act_img2:
                    intent.putExtra("activity",tag.get(1));
                    context.startActivity(intent);
                    break;
                case R.id.act_img3:
                    intent.putExtra("activity",tag.get(2));
                    context.startActivity(intent);
                    break;
                case R.id.more_tv:
                    Snackbar.make(TagsView.this,type,Snackbar.LENGTH_LONG).show();
                    break;
            }
        }
    }


    public String[] getTabsString(String s){
        String[] types=context.getResources().getStringArray(R.array.activity_type);
        if(s==null||!s.contains(","))return null;
        else{
            String[] result=s.split(",");
            for (int i = 0; i <result.length ; i++) {

            }
        }
        return  null;
    }
}
