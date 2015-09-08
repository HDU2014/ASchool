package com.hdu.tx.aschool.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseFragment;
import com.hdu.tx.aschool.ui.adapter.AdAdapter;
import com.hdu.tx.aschool.ui.adapter.RecyclerViewAdapter;
import com.hdu.tx.aschool.ui.widget.pupuwindow.SelectItemsPop;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/8/23.
 */
public class AdFragment extends BaseFragment {
    @Bind(R.id.schools)
    TextView schools;
    @Bind(R.id.types)
    TextView types;
    @Bind(R.id.time)
    TextView time;

    @Bind(R.id.main_content)
    LinearLayout mainContent;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ad, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(-5));
        recyclerView.setAdapter(new AdAdapter(getActivity()));
    }


    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if(parent.getChildPosition(view) == 0)
                outRect.top = space;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.schools)
    void onclick1() {
        final String[] schoolsData = getResources().getStringArray(R.array.activity_schools);
        final SelectItemsPop pop = new SelectItemsPop(getActivity(), schoolsData, schools);

        pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                schools.setText(schoolsData[position]);
                pop.dismiss();
            }
        });
    }


    @OnClick(R.id.types)
    void onclick2() {
        final String[] schoolsData = getResources().getStringArray(R.array.activity_type);
        final SelectItemsPop pop = new SelectItemsPop(getActivity(), schoolsData, types);

        pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                types.setText(schoolsData[position]);
                pop.dismiss();
            }
        });
    }

    @OnClick(R.id.time)
    void onclick3() {
        final String[] schoolsData = getResources().getStringArray(R.array.activity_time);
        final SelectItemsPop pop = new SelectItemsPop(getActivity(), schoolsData, time);

        pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                time.setText(schoolsData[position]);
                pop.dismiss();
            }
        });
    }
}
