package com.hdu.tx.aschool.ui.adapter;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.ui.widget.image.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2015/9/29.
 */
public class FriendsAdapter extends BaseAdapter {
    private Context context;
    private List<UserInfo> datas;
    private LayoutInflater inflate;

    public FriendsAdapter(Context context, List<UserInfo> datas) {
        this.context = context;
        this.datas = datas;
        inflate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        UserInfo userInfo=datas.get(position);
        if (convertView == null) {
            convertView = inflate.inflate(R.layout.friends_list, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(userInfo.getHeadimg_url()).into(viewHolder.headImg);
        viewHolder.nicknameTv.setText(userInfo.getNickname());
        viewHolder.jieshaoTv.setText(userInfo.getSchool()+"  "+userInfo.getInstitute());
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'friends_list.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.head_img)
        CircleImageView headImg;
        @Bind(R.id.nickname_tv)
        TextView nicknameTv;
        @Bind(R.id.jieshao_tv)
        TextView jieshaoTv;
        @Bind(R.id.add_bt)
        Button addBt;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
