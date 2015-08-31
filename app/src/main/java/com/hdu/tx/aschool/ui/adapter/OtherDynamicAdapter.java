package com.hdu.tx.aschool.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.ui.activity.AdDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;

/**
 * Created by pualgo on 2015/8/31.
 */
public class OtherDynamicAdapter extends RecyclerView.Adapter<OtherDynamicAdapter.ViewHolder> {

    private Context mContext;
    private List<ActInfo> actInfos;

    public OtherDynamicAdapter(Context context, List<ActInfo> listdata) {
        this.mContext = context;
        this.actInfos = listdata;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_cardview, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final View view = holder.mView;
        final ActInfo actInfo = actInfos.get(position);

        String path = actInfo.getImageUrl();
        if (path != null && !"".equals(path))
            Picasso.with(mContext).load(actInfo.getImageUrl()).into(holder.actimg_iv);
        String joined = "<font color='blue'>" + actInfo.getJoinedpeopel() + "</font>  报名";
        holder.joined_tv.setText(Html.fromHtml(joined));
        holder.collect_tv.setText(actInfo.getCollectTimes() + "");
        holder.hostname_tv.setText(actInfo.getHostname());
        holder.time_tv.setText(actInfo.getTime());
        holder.address_tv.setText(actInfo.getAddress());
        holder.title_tv.setText(actInfo.getTitle());
        holder.dynamic_type.setText("发起");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 5, 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mContext.startActivity(new Intent(mContext, AdDetailActivity.class).putExtra("activity", actInfo));
                    }
                });
                animator.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return actInfos == null ? 0 : actInfos.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title_tv;
        public final TextView time_tv;
        public final TextView hostname_tv;
        public final TextView address_tv;
        public final TextView collect_tv;
        public final TextView joined_tv;
        public final ImageView actimg_iv;
        public final TextView dynamic_type;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title_tv = (TextView) view.findViewById(R.id.event_title);
            time_tv = (TextView) view.findViewById(R.id.event_time);
            hostname_tv = (TextView) view.findViewById(R.id.host);
            address_tv = (TextView) view.findViewById(R.id.event_address);
            collect_tv = (TextView) view.findViewById(R.id.event_collect);
            joined_tv = (TextView) view.findViewById(R.id.event_joined);
            actimg_iv = (ImageView) view.findViewById(R.id.event_image);
            dynamic_type = (TextView) view.findViewById(R.id.dynamic_type);
        }
    }


}