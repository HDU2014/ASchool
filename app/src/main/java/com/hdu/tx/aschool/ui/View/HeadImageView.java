package com.hdu.tx.aschool.ui.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.ui.widget.image.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by chenglin on 2015/9/6.
 */
public class HeadImageView extends LinearLayout{
    private static final String TAG = HeadImageView.class.getSimpleName();
    private List<UserInfo> userInfos;
    private Context context;
    private int widthSize=45;
    private int width;

    public HeadImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public HeadImageView(Context context) {
        super(context);
        this.context=context;
    }

    public HeadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    public void setDate(List<UserInfo> userInfos){
        this.userInfos=userInfos;
        onMyDraw();
    }


    public void onMyDraw(){
        int scale=8;
        int size=userInfos.size()>scale?scale:userInfos.size();
        float density = context.getResources().getDisplayMetrics().density;
        int finalDimens = (int)(widthSize * density);
        LinearLayout.LayoutParams imgvwDimens =new LinearLayout.LayoutParams(finalDimens, finalDimens);
        for (int i = 0; i <userInfos.size() ; i++) {
            CircleImageView imageView=new CircleImageView(context);
            imageView.setLayoutParams(imgvwDimens);
            Picasso.with(context).load(userInfos.get(i).getHeadimg_url()).into(imageView);
            this.addView(imageView);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
