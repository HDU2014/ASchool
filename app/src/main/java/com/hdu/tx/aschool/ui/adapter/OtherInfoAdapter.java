package com.hdu.tx.aschool.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.entity.OtherInfoEntity;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/10/14.
 */
public class OtherInfoAdapter extends RecyclerView.Adapter<OtherInfoAdapter.ViewHolder> {
    private Activity context;
    private List<OtherInfoEntity> mData;

    public OtherInfoAdapter(Activity context,List<OtherInfoEntity> mData) {
        this.context = context;
        this.mData=mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.otherinfo_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvLeft.setText(mData.get(position).getText1());
        holder.tvRight.setText(mData.get(position).getText2());
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'otherinfo_cardview.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder extends  RecyclerView.ViewHolder{
        @Bind(R.id.tv_left)
        TextView tvLeft;
        @Bind(R.id.tv_right)
        TextView tvRight;
        @Bind(R.id.article_cardview)
        CardView articleCardview;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
