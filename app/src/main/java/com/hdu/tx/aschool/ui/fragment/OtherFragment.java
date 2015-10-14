package com.hdu.tx.aschool.ui.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.BaseFragment;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.entity.OtherInfoEntity;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.JSONHandler;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.net.Urls;
import com.hdu.tx.aschool.ui.activity.OtherInfoActivity;
import com.hdu.tx.aschool.ui.adapter.OtherInfoAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pualgo on 2015/8/28.
 */

public class OtherFragment extends BaseFragment {
    public View otherView;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;


    private BaseActivity superActivity;
    private OtherInfoActivity otherInfoActivity;

    private UserInfo userInfo;
    public String hostId;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.superActivity = (BaseActivity) getActivity();
        otherInfoActivity = (OtherInfoActivity) getActivity();
        userInfo = otherInfoActivity.getUserInfo();
        setData();
        // getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        otherView = inflater.inflate(R.layout.fragment_otherinfo, container, false);
        ButterKnife.bind(this, otherView);
        return otherView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void setData() {
        List<OtherInfoEntity> datas = new ArrayList<>();



        OtherInfoEntity infoEntity = new OtherInfoEntity();
        infoEntity.setText1("年龄");
        infoEntity.setText2(userInfo.getAge());
        datas.add(infoEntity);

        OtherInfoEntity infoEntity1 = new OtherInfoEntity();
        infoEntity1.setText1("城市");
        infoEntity1.setText2(userInfo.getCity());
        datas.add(infoEntity1);

        OtherInfoEntity infoEntity2 = new OtherInfoEntity();
        infoEntity2.setText1("学校");
        infoEntity2.setText2(userInfo.getSchool());
        datas.add(infoEntity2);

        OtherInfoEntity infoEntity3 = new OtherInfoEntity();
        infoEntity3.setText1("学院");
        infoEntity3.setText2(userInfo.getInstitute());
        datas.add(infoEntity3);

        OtherInfoEntity infoEntity5 = new OtherInfoEntity();
        infoEntity5.setText1("年级");
        infoEntity5.setText2(userInfo.getGrade());
        datas.add(infoEntity5);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        //recyclerView.addItemDecoration(new ItemDivider(getActivity()));
        OtherInfoAdapter adapter=new OtherInfoAdapter(this.getActivity(),datas);
        recyclerView.setAdapter(adapter);
    }

    public void getData() {
        new MyStringRequest(Urls.USER_QUERY_BYID, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                userInfo = JSONHandler.json2UserInfo(json);

            }

            @Override
            public void error(String desc) {

            }

            @Override
            public Map<String, String> setParams() {
                Map<String, String> map = new HashMap<>();
                map.put("user_name", userInfo.getUsername());
                return map;
            }
        });
    }

    public class ItemDivider extends RecyclerView.ItemDecoration {

        private Drawable mDrawable;

        public ItemDivider(Context context) {
            //在这里我们传入作为Divider的Drawable对象
            mDrawable=getResources().getDrawable(R.drawable.mm_listitem_simple);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                //以下计算主要用来确定绘制的位置
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDrawable.getIntrinsicHeight();
                mDrawable.setBounds(left, top, right, bottom);
                mDrawable.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, int position, RecyclerView parent) {
            outRect.set(0, 0, 0, mDrawable.getIntrinsicWidth());
        }
    }

}
