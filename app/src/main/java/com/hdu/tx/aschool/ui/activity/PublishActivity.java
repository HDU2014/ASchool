package com.hdu.tx.aschool.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dd.processbutton.iml.ActionProcessButton;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.common.utils.MySecurity;
import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.net.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pualgo on 2015/8/24.
 */
public class PublishActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.Image_content)
    ImageView ImageContent;
    @Bind(R.id.et_tittle)
    EditText etTitle;
    @Bind(R.id.chooseType)
    TextView chooseType;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.btime)
    EditText btime;
    @Bind(R.id.et_peopleNo)
    EditText etPeopleNo;
    @Bind(R.id.et_address)
    EditText etAddress;

    @Bind(R.id.et_introdue)
    EditText etIntrodue;

    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Bind(R.id.choiceType)
    LinearLayout choiceType;

    String dateSet;
    Calendar calendar;
    public static final int REQUSET = 1;
    public static final String MSG = "MSG";
    @Bind(R.id.checkbox)
    CheckBox checkbox;
    @Bind(R.id.btPublish)
    ActionProcessButton btPublish;
    String mTime;
    String mDate;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        calendar = Calendar.getInstance();
        //起始时间编辑框监听
        btime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                btime.setInputType(InputType.TYPE_NULL);
                if (hasFocus) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(PublishActivity.this, DateSet, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));
                    TimePickerDialog timePickerDialog = new TimePickerDialog(PublishActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            calendar.set(Calendar.MINUTE, minute);

                            mTime = "  " + hourOfDay + ":" + minute;
                            String str = mDate + mTime;
                            if (btime.isFocused()) {
                                btime.setText(str);

                            }
                        }
                    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                    timePickerDialog.show();
                    datePickerDialog.show();
                }
            }
        });
        //活动介绍弹出对话框
        etIntrodue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etIntrodue.setInputType(InputType.TYPE_NULL);
                Intent intent = new Intent(PublishActivity.this, PartyIntrodue.class);
                String str = etIntrodue.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putSerializable(MSG, str);
                intent.putExtras(bundle);
                startActivityForResult(intent, REQUSET);
            }
        });


        choiceType.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PublishActivity.this);
                builder.setTitle("选择类型");
                final String[] choose = PublishActivity.this.getResources().getStringArray(R.array.activity_type);
                builder.setItems(choose, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        chooseType.setText(choose[which]);
                    }
                });
                builder.show();
            }
        });
        btPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("publish", "gongo");
                publish();
                //getActFromServer();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PublishActivity.REQUSET && resultCode == RESULT_OK) {
            String str = data.getStringExtra(PartyIntrodue.MSG);
            etIntrodue.setText(str);

        }

    }


    DatePickerDialog.OnDateSetListener DateSet = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // 每次保存设置的日期
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            mDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

        }
    };

    public void publish() {
        btPublish.setProgress(30);
        try {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.PUBLISH, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    try {
                        JSONObject object = new JSONObject(s);
                        int code = object.getInt("result");
                        if (code == 200) {
                            btPublish.setProgress(100);
                            getActFromServer(object.getString("act_id"));

                        } else {
                            btPublish.setProgress(-1);
                        }
                    } catch (JSONException e) {
                        btPublish.setProgress(-1);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    btPublish.setProgress(-1);
                    // btRegist.setProgress(-1);
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    // Map<String, String> map = new HashMap<>();
//                    map.put("user_name",etPhone.getText().toString());
//                    String pwd_md5 = new MySecurity().encodyByMD5(etPass.getText().toString());
//                    map.put("password",pwd_md5);
                    return generateMap();
                }
            };
            getVolleyQueue().add(stringRequest);

        } catch (Exception e) {
            toast(toolbar, e.toString());
        }
    }

    public Map<String, String> generateMap() {
        Map<String, String> map = new HashMap<>();
        map.put("title", etTitle.getText().toString());
        map.put("act_tag", chooseType.getText().toString());
        map.put("start_time", btime.getText().toString());
        map.put("act_num", etPeopleNo.getText().toString());
        map.put("act_place", etAddress.getText().toString());
        map.put("content", etIntrodue.getText().toString());
        String str=checkbox.isChecked()?"true":"false";
        map.put("open", str);
        map.put("user_name", etPhone.getText().toString());
        return map;
    }


    public void getActFromServer(final String id){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.GET_ACTIVITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject object=new JSONObject(s);
                    if(object.getInt("result")==200){
                        ActInfo info=new ActInfo();
                        info.setTitle(object.getString("title"));
                        info.setTime(object.getString("start_time"));
                        info.setTotalpeopel(object.getInt("act_num"));
                        info.setAddress(object.getString("act_place"));
                        info.setDescribe(object.getString("content"));
                        info.setHostname(object.getString("user_name"));
                        info.setJoinedpeopel(object.getInt("join_num"));
                        info.setCollectTimes(object.getInt("collect_num"));
                        info.setLookTimes(object.getInt("browse_num"));
                        MyApplication.getInstance().getDaoSession().getActInfoDao().insert(info);
                        Intent intent=new Intent(PublishActivity.this,AdDetailActivity.class);
                        intent.putExtra("activity",info);
                        PublishActivity.this.startActivity(intent);
                    }else{
                        toast(toolbar,object.getString("desc"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    toast(toolbar,e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("act_id",id);
                return map;
            }
        };
        getVolleyQueue().add(stringRequest);
    }

}