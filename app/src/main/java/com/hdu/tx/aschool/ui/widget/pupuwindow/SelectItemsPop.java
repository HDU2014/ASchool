package com.hdu.tx.aschool.ui.widget.pupuwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.hdu.tx.aschool.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/23.
 */
public class SelectItemsPop extends PopupWindow{
    private ListView listView;

    public SelectItemsPop(Context context,ArrayList<String> data,View superview) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.selectitem, null);
        listView = (ListView) view.findViewById(R.id.list);
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0xffffffff);
       //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.popwinw_style);
        listView.setAdapter(new ArrayAdapter<String>(context, R.layout.spinner_text, data));
        this.showAsDropDown(superview, 0, 0);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        if(listView!=null)listView.setOnItemClickListener(listener);
    }
}
