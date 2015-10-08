package com.hdu.tx.aschool.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.entity.InterestTabsEntitiy;
import com.hdu.tx.aschool.ui.IEvent.OnRecyleViewItemListner;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.smssdk.gui.GroupListView;

/**
 * Created by pualgo on 2015/8/31.
 */
public class InterestTabsAdapter extends RecyclerView.Adapter<InterestTabsAdapter.ViewHolder> {
    public Context mContext;
    public List<InterestTabsEntitiy>  mData;
    private LayoutInflater inflater;
    private int ClickItem = -1;
    private boolean isChoosed = false;
    private OnRecyleViewItemListner listner;

    public InterestTabsAdapter(Context context,List<InterestTabsEntitiy> data) {
        this.mContext = context;
        this.mData = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interest_tabs_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final InterestTabsEntitiy tab=mData.get(position);
       holder.tabTv.setText(tab.getTab());
        holder.tabCb.setSelected(tab.isSelected());
        MyOnClickListner myListner=new MyOnClickListner(tab,holder);
       holder.tabCv.setOnClickListener(myListner);
        holder.tabCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tab.setIsSelected(isChecked);
                selectTab(tab,holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'interest_tabs_card.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */


    public static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tab_cb)
        CheckBox tabCb;
        @Bind(R.id.tab_tv)
        TextView tabTv;
        @Bind(R.id.tab_cv)
        CardView tabCv;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setOnItemsClickListner(OnRecyleViewItemListner listner){
        this.listner=listner;
    }


    private class MyOnClickListner implements View.OnClickListener{
        InterestTabsEntitiy tab;
        ViewHolder holder;
        public MyOnClickListner(InterestTabsEntitiy tab,ViewHolder holder){
            this.tab=tab;
            this.holder=holder;
        }

        @Override
        public void onClick(View v) {
            tab.setIsSelected(!tab.isSelected());
            listner.onClick(v, tab.getTab());
            selectTab(tab,holder);
        }
    }


    public void selectTab(InterestTabsEntitiy tab,ViewHolder holder){
        holder.tabCb.setChecked(tab.isSelected());
        holder.tabCv.setCardBackgroundColor(tab.isSelected() ? mContext.getResources().getColor(R.color.colorPrimary) : mContext.getResources().getColor(R.color.yellow));
    }
}
