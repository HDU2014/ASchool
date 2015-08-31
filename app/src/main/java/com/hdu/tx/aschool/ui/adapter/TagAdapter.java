package com.hdu.tx.aschool.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
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
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.entity.MyTagEntity;
import com.hdu.tx.aschool.ui.activity.AdDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {

    private Context mContext;
    private List<MyTagEntity> tagEntities;

    public TagAdapter(Context mContext, List<MyTagEntity> tagEntities){
        this.mContext = mContext;
        this.tagEntities=tagEntities;
    }

    @Override
    public TagAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tag, parent, false);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final TagAdapter.ViewHolder holder, int position) {
        final View view = holder.mView;
        final MyTagEntity tagEntity=tagEntities.get(position);
        MyItemOnClickListener listener=new MyItemOnClickListener(tagEntity);
        for (int i = 0; i <3 ; i++) {
            ActInfo info=tagEntity.getActInfos().get(i);
            String url=info.getImageUrl();
            if(url!=null&&!url.equals(""))
                Picasso.with(mContext).load(url).into(holder.imageViews[i]);
            holder.title[i].setText(info.getTitle());
            holder.imageViews[i].setOnClickListener(listener);
        }
        holder.tag_tv.setText(tagEntity.getType());
        holder.more_tv.setOnClickListener(listener);

    }



    @Override
    public int getItemCount() {
        return tagEntities==null?0:tagEntities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private ImageView[] imageViews;
        private TextView[]  title;
        private TextView tag_tv;
        private TextView more_tv;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageViews=new ImageView[3];
            title=new TextView[3];
            imageViews[0]= (ImageView)view.findViewById(R.id.act_img1);
            imageViews[1]= (ImageView)view.findViewById(R.id.act_img2);
            imageViews[2]= (ImageView)view.findViewById(R.id.act_img3);
            title[0]= (TextView) view.findViewById(R.id.act_title1);
            title[1]= (TextView) view.findViewById(R.id.act_title2);
            title[2]= (TextView) view.findViewById(R.id.act_title3);
            tag_tv= (TextView) view.findViewById(R.id.act_type);
            more_tv= (TextView) view.findViewById(R.id.more_tv);
        }
    }



    public class MyItemOnClickListener implements View.OnClickListener{

        private MyTagEntity tag;
        public MyItemOnClickListener(MyTagEntity tag) {
            this.tag=tag;
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(mContext,AdDetailActivity.class);
            switch (v.getId()){
                case R.id.act_img1:
                    intent.putExtra("activity",tag.getActInfos().get(0));
                    mContext.startActivity(intent);
                    break;
                case R.id.act_img2:
                    intent.putExtra("activity",tag.getActInfos().get(1));
                    mContext.startActivity(intent);
                    break;
                case R.id.act_img3:
                    intent.putExtra("activity",tag.getActInfos().get(2));
                    mContext.startActivity(intent);
                    break;
                case R.id.more_tv:
                    break;
            }
        }
    }
}
