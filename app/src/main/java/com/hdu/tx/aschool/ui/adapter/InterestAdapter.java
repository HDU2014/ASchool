package com.hdu.tx.aschool.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hdu.tx.aschool.R;

/**
 * Created by pualgo on 2015/8/31.
 */
public class InterestAdapter extends BaseAdapter {
    public Context mContext;
    public String[] mData;
    private LayoutInflater inflater;
    private int ClickItem = -1;
    private boolean isChoosed= false;




    public InterestAdapter(Context context, String[] strings) {
        this.mContext = context;
        this.mData = strings;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public Object getItem(int position) {
        return mData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getClickItem() {
        return ClickItem;
    }

    public void setClickItem(int clickItem) {
        ClickItem = clickItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.interest_card, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.interest_card);
        textView.setText(mData[position]);
//        setClickItem(position);
        notifyDataSetChanged();
        if (ClickItem==position)
        {
            if(isChoosed==false) {
                textView.setBackgroundResource(R.drawable.interest_bg1);
                isChoosed=true;
            }
            else {
                textView.setBackgroundResource(R.drawable.interest_bg);
                isChoosed = false;
            }
        }

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        getClickItem();
    }
}
