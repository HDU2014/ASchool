package com.hdu.tx.aschool.ui.adapter;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.ui.IEvent.LunBoOnClickListener;
import com.hdu.tx.aschool.ui.activity.AdDomain;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenglin on 2015/8/30.
 */
public class LunBoAdapter extends PagerAdapter {
    private List<ActInfo> actInfos;
    private BaseActivity activity;
    private List<ImageView> imageViews;
    private List<View> dots;
    private List<View> dotList;
    private ViewPager viewPager;
    private int currentItem;
    private LunBoOnClickListener lunBoOnClickListener;

    private TextView tv_title;
    private TextView tv_data;
    private TextView tv_address;


    private ScheduledExecutorService scheduledExecutorService;


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            viewPager.setCurrentItem(currentItem);
        };
    };


    public LunBoAdapter(BaseActivity activity,View view,List<ActInfo> actInfos) {
        this.actInfos=actInfos;
        this.activity=activity;
        imageViews = new ArrayList<>();
         if(view!=null)initView(view);
    }


    public void initView(View view){
        dots = new ArrayList<>();
        dotList = new ArrayList<>();
        dots.add(view.findViewById(R.id.v_dot0));
        dots.add(view.findViewById(R.id.v_dot1));
        dots.add(view.findViewById(R.id.v_dot2));
        dots.add(view.findViewById(R.id.v_dot3));
        dots.add(view.findViewById(R.id.v_dot4));

        tv_title= (TextView) view.findViewById(R.id.tv_title);
        tv_data= (TextView) view.findViewById(R.id.tv_date);
        tv_address= (TextView) view.findViewById(R.id.tv_address);

        addDynamicView();
        viewPager= (ViewPager) view.findViewById(R.id.vp);
        viewPager.setAdapter(this);
        viewPager.setOnPageChangeListener(new MyPageChangeListener());

        startAd();
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = imageViews.get(position);
        ((ViewPager) container).addView(iv);
        final ActInfo actInfo = actInfos.get(position);
        // 在这个方法里面设置图片的点击事件
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lunBoOnClickListener!=null)lunBoOnClickListener.onClick(actInfo);
            }
        });
        return iv;
    }


    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        private int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            ActInfo actInfo=actInfos.get(position);
            tv_title.setText(actInfo.getTitle()); // 设置标题
           tv_data.setText(actInfo.getTime());
            tv_address.setText(actInfo.getAddress());
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }

    private void addDynamicView() {
        // 动态添加图片和下面指示的圆点
        // 初始化图片资源
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(activity);
            // 异步加载图片
            String url=actInfos.get(i).getImageUrl();
            if(url!=null&&!url.equals(""))
            Picasso.with(activity).load(actInfos.get(i).getImageUrl()).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
            dots.get(i).setVisibility(View.VISIBLE);
            dotList.add(dots.get(i));
        }
    }

    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 5,
                TimeUnit.SECONDS);
    }

    private class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (viewPager) {
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    public void setLunBoOnClickListener(LunBoOnClickListener lunBoOnClickListener){
        this.lunBoOnClickListener=lunBoOnClickListener;
    }

}
