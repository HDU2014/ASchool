package com.hdu.tx.aschool.ui.widget.pupuwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.hdu.tx.aschool.R;

/**
 * Created by Administrator on 2015/8/23.
 */
public class SelectItemsPop1 extends PopupWindow{
    private final Animation dropdown_mask_out;
    private ListView listView;
    private DropdownButton dropdownButton;

    private Activity activity;
    private View mask;

    public SelectItemsPop1(Context context, String[] data, View superview,View mask) {
        super(context);
        dropdownButton= (DropdownButton) superview;
        this.mask=mask;
        this.activity= (Activity) context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.selectitem, null);
        listView = (ListView) view.findViewById(R.id.list);
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        ColorDrawable dw = new ColorDrawable(0xffffffff);
       //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        dropdown_mask_out = AnimationUtils.loadAnimation(context, R.anim.dropdown_mask_out);

        this.setAnimationStyle(R.style.popwinw_style);
        listView.setAdapter(new ArrayAdapter<String>(context, R.layout.spinner_text, data));

    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        if(listView!=null)listView.setOnItemClickListener(listener);
        dropdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectItemsPop1.this.show();
            }
        });
    }

    public void show(){
        this.showAsDropDown(dropdownButton, 0, 0);
        dropdownButton.setChecked(true);
        mask.setVisibility(View.VISIBLE);
        mask.startAnimation(dropdown_mask_out);
        mask.clearAnimation();


    }


    @Override
    public void dismiss() {
        mask.setVisibility(View.GONE);
        mask.clearAnimation();
        mask.startAnimation(dropdown_mask_out);
        dropdownButton.setChecked(false);
        super.dismiss();
    }


}
