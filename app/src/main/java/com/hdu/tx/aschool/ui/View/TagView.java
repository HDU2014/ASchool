package com.hdu.tx.aschool.ui.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.ui.activity.AdDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by chenglin on 2015/8/30.
 */
public class TagView extends CardView {
    private Context context;
    private ImageView[] imageViews;
    private TextView[]  title;
    private TextView tag_tv;
    private TextView more_tv;
    private String tag;
    private List<ActInfo> actInfos;

    public TagView(Context context) {
        super(context);
        this.context=context;


    }

    public TagView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView(){
        imageViews=new ImageView[3];
        title=new TextView[3];
        imageViews[0]= (ImageView) findViewById(R.id.act_img1);
        imageViews[1]= (ImageView) findViewById(R.id.act_img2);
        imageViews[2]= (ImageView) findViewById(R.id.act_img3);
        title[0]= (TextView) findViewById(R.id.act_title1);
        title[1]= (TextView) findViewById(R.id.act_title2);
        title[2]= (TextView) findViewById(R.id.act_title3);
        tag_tv= (TextView) findViewById(R.id.act_type);
        more_tv= (TextView) findViewById(R.id.more_tv);
        tag_tv.setText("类型");
        more_tv.setText("更多");
    }

    public void setTag(String tag){
        tag_tv.setText(tag);
    }

    public void setOnClickListener(View.OnClickListener listener){
        for (int i = 0; i <3 ; i++) {
            imageViews[i].setOnClickListener(listener);
        }
    }

    public void setActDate(List<ActInfo> actInfos){
        this.actInfos=actInfos;
        for (int i = 0; i <3 ; i++) {
            String url=actInfos.get(i).getImageUrl();
            if(url!=null&&!url.equals(""))
                Picasso.with(context).load(actInfos.get(i).getImageUrl()).into(imageViews[i]);
            title[i].setText(actInfos.get(i).getTitle());
        }
    }


}
