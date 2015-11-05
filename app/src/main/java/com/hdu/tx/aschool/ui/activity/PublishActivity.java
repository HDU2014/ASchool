package com.hdu.tx.aschool.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.dd.processbutton.iml.SubmitProcessButton;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.common.utils.ConstantValue;
import com.hdu.tx.aschool.common.utils.PhotoUtil;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.net.Urls;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pualgo on 2015/8/24.
 */
public class PublishActivity extends BaseActivity {
    public static final String TAG = "PublishActivity";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.image_content)
    ImageView imageContent;
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


    @Bind(R.id.choiceType)
    LinearLayout choiceType;

    String dateSet;
    Calendar calendar;
    public static final int REQUSET = 1;
    public static final String MSG = "MSG";
    @Bind(R.id.checkbox)
    CheckBox checkbox;
    @Bind(R.id.btPublish)
    SubmitProcessButton btPublish;
    String mTime;
    String mDate;
    String id;
    private String imagePath;

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
                publishActivity();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PublishActivity.REQUSET && resultCode == RESULT_OK) {
            String str = data.getStringExtra(PartyIntrodue.MSG);
            etIntrodue.setText(str);
        } else if (requestCode == ConstantValue.INTENT_SELECT_PHOTOS) {
            if (data != null) {
                Uri photoUri = data.getData();
                if (photoUri != null) PhotoUtil.cameraCropImageUri(this, photoUri, 16, 9, 800, 450);
            }
        } else if (requestCode == ConstantValue.INTENT_AFTER_CROPPHOTO) {
            String path = ConstantValue.CROP_TEMP_PATH;
            Bitmap b = BitmapFactory.decodeFile(path);
            if (b != null) {
                imageContent.setImageBitmap(b);
                imagePath = path;
            }

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

    public void saveActivity(final String key) {
        new MyStringRequest(Urls.ACTIVITY_PUBLISH, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                btPublish.setProgress(100);
                btPublish.setEnabled(true);
            }

            @Override
            public void error(String desc) {
                btPublish.setProgress(-1);
                btPublish.setEnabled(true);
                toast(toolbar, desc);
            }

            @Override
            public Map<String, String> setParams() {
                Map<String, String> map = generateMap();
                map.put("img_url", key);
                return map;
            }
        });
    }
//    @OnClick(R.id.add_edit)
//    void addEdit() {
//        LinearLayout linearLayout = new LinearLayout(this);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        linearLayout.setBackgroundResource(R.color.white);
//        params.setMargins(0,10,0,0);
//
//
//        edit_id++;
//        EditText et = new EditText(this);
//        et.setTextSize(15f);
//        et.setId(edit_id);
//        et.setHint("请输入你需要的信息");
//        et.setHintTextColor(getResources().getColor(R.color.grey_little));
//        et.setGravity(Gravity.LEFT);
//        et.setBackgroundResource(R.drawable.edit_no_stroke);
//        linearLayout.addView(et,params);
//        bminfo.addView(linearLayout);
//
//
//    }

    @OnClick(R.id.image_content)
    void selectImage() {
        PhotoUtil.startSelectImageFromLocal(this);
    }

    public Map<String, String> generateMap() {
        Map<String, String> map = new HashMap<>();
        map.put("title", etTitle.getText().toString());
        map.put("act_tag", chooseType.getText().toString());
        map.put("start_time", btime.getText().toString());
        map.put("act_num", etPeopleNo.getText().toString());
        map.put("act_place", etAddress.getText().toString());
        map.put("content", etIntrodue.getText().toString());
        String str = checkbox.isChecked() ? "true" : "false";
        map.put("open", str);
        map.put("user_name", MyApplication.getInstance().getUserInfo().getUsername());
        map.put("act_phone_num", etPhone.getText().toString());
        return map;
    }

    public void getQiniuToken() {
        new MyStringRequest(Urls.USER_GET_UPTOKEN, new InternetListener() {
            @Override
            public void success(JSONObject json) {
                String up_token = null;
                try {
                    up_token = json.getString("up_token");
                    String img_key = json.getString("img_key");
                    Log.i(TAG, "upToken--->" + up_token + "Key--->" + img_key);
                    updateImage(imagePath, up_token, img_key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String desc) {
                toast(toolbar, desc);
                btPublish.setEnabled(true);
            }

            @Override
            public Map<String, String> setParams() {
                Map<String, String> map = new HashMap<>();
                map.put("user_name", MyApplication.getInstance().getUserInfo().getUsername());
                return map;
            }
        });
    }


    public void updateImage(final String img_path, final String upToken, final String img_key) {
        UploadManager uploadManager = new UploadManager();
        uploadManager.put(img_path, img_key, upToken, new UpCompletionHandler() {
            @Override
            public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
                Log.i("TAG--updateImage", s);
                btPublish.setProgress(90);
                saveActivity(s);
            }

        }, new UploadOptions(null, null, false, new UpProgressHandler() {
            @Override
            public void progress(String s, double v) {
                int what = (int) (v * 90);
                handler.sendEmptyMessage(what);
            }
        }, null));
    }


    private Message msg = new Message();

    public void publishActivity() {
        if (imagePath == null) {
            Snackbar.make(toolbar, "对不起，您还未选择活动的图片", Snackbar.LENGTH_LONG).
                    setAction("挑选图片", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PhotoUtil.startSelectImageFromLocal(PublishActivity.this);
                        }
                    }).show();
            return;
        }
        btPublish.setEnabled(false);
        btPublish.setProgress(1);

        getQiniuToken();
    }


    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            btPublish.setProgress(msg.what);
        }
    };


}