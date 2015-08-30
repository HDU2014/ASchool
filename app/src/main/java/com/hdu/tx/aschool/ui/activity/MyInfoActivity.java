package com.hdu.tx.aschool.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.base.BaseActivity;
import com.hdu.tx.aschool.base.MyApplication;
import com.hdu.tx.aschool.common.utils.ConstantValue;
import com.hdu.tx.aschool.common.utils.PhotoUtil;
import com.hdu.tx.aschool.dao.DaoSession;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.net.Urls;
import com.hdu.tx.aschool.net.InternetListener;
import com.hdu.tx.aschool.net.MyStringRequest;
import com.hdu.tx.aschool.ui.widget.image.CircleImageView;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/8/14.
 */
public class MyInfoActivity extends BaseActivity {
    private static final String TAG ="MyInfoActivity" ;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.head_img)
    CircleImageView headImg;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.nickname_ll)
    LinearLayout nicknameLl;
    @Bind(R.id.sex_ll)
    LinearLayout sexll;
    @Bind(R.id.sex_tv)
    TextView sextv;
    @Bind(R.id.nickname_tv)
    TextView nicknameTv;
    @Bind(R.id.school_tv)
    TextView schoolTv;
    @Bind(R.id.school_ll)
    LinearLayout schoolLl;
    @Bind(R.id.grade_tv)
    TextView gradeTv;
    @Bind(R.id.grade_ll)
    LinearLayout gradeLl;
    @Bind(R.id.insititude_tv)
    TextView insititudeTv;
    @Bind(R.id.insititude_ll)
    LinearLayout insititudeLl;
    @Bind(R.id.phone_tv)
    TextView phoneTv;
    @Bind(R.id.phone_ll)
    LinearLayout phoneLl;
    @Bind(R.id.city_tv)
    TextView cityTv;
    @Bind(R.id.city_ll)
    LinearLayout cityLl;
    @Bind(R.id.age_tv)
    TextView ageTv;
    @Bind(R.id.age_ll)
    LinearLayout ageLl;

    private Intent intent;
    private Bundle bundle;
    private UserInfo userInfo;
    private DaoSession daoSession;
    private ProgressDialog progressDialog;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.my_info);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("正在发送请求...");

        intent = new Intent(MyInfoActivity.this, UpdateInfoActivity.class);
        bundle = new Bundle();
        userInfo= MyApplication.getInstance().getUserInfo();
        daoSession= MyApplication.getInstance().getDaoSession();
        refreshMyInfo();

    }

    private void refreshMyInfo() {
        Picasso.with(this).load(userInfo.getHeadimg_url()).into(headImg);
        nicknameTv.setText(userInfo.getNickname());
        Log.i("TAG", userInfo.getHeadimg_url());
        sextv.setText(userInfo.getSex());
        ageTv.setText(userInfo.getAge());
        schoolTv.setText(userInfo.getSchool());
        gradeTv.setText(userInfo.getGrade());
        insititudeTv.setText(userInfo.getInstitute());
        phoneTv.setText(userInfo.getPhoneNumber());
        cityTv.setText(userInfo.getCity());
    }

    @OnClick(R.id.head_img_ll)void setHeadImg(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选项").setItems(new String[]{
                "拍照", "相册"
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    photoPath=PhotoUtil.startIntentTakePhotos(MyInfoActivity.this);
                }else if(which==1){
                    PhotoUtil.startSelectImageFromLocal(MyInfoActivity.this);
                }
            }
        }).create().show();
    }


    @OnClick(R.id.nickname_ll)void setNickname(){

        bundle.putString("title", "修改昵称");
        bundle.putString("descript", "修改昵称让好友们都记住你吧！");
        bundle.putString("hint", nicknameTv.getText().toString());
        bundle.putBoolean("isNumber", false);
        bundle.putString("param", "nick_name");
        intent.putExtras(bundle);
        MyInfoActivity.this.startActivityForResult(intent, 6);
    }

    @OnClick(R.id.sex_ll)void setSex(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MyInfoActivity.this);
        builder.setTitle("请选择性别");
        final String[] sex = {"男", "女"};
        if(!progressDialog.isShowing())progressDialog.show();
        builder.setItems(sex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String value = sex[which];
                submit("gender", sex[which], new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (progressDialog.isShowing()) progressDialog.dismiss();
                        try {
                            JSONObject object = new JSONObject(s);
                            if (object.getInt("result") == 200) {
                                sextv.setText(value);
                                userInfo.setSex(value);
                                daoSession.update(userInfo);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Snackbar.make(toolbar, e.toString(), Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        builder.show();
    }

    @OnClick(R.id.age_ll)void setAge(){
        bundle.putString("title", "修改年龄");
        bundle.putString("descript", "修改你的年龄让我们为你推荐最好的活动");
        bundle.putString("hint", ageTv.getText().toString());
        bundle.putBoolean("isNumber", true);
        bundle.putString("param", "user_age");
        intent.putExtras(bundle);
        MyInfoActivity.this.startActivityForResult(intent, 5);
    }

    @OnClick(R.id.school_ll)void setSchool(){
        bundle.putString("title", "修改学校");
        bundle.putString("descript", "修改你的学校让更多的校友发现你们");
        bundle.putString("hint", schoolTv.getText().toString());
        bundle.putBoolean("isNumber", false);
        bundle.putString("param", "user_school");
        intent.putExtras(bundle);
        MyInfoActivity.this.startActivityForResult(intent, 4);
    }

    @OnClick(R.id.grade_ll)void setGrade(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MyInfoActivity.this);
        builder.setTitle("请选择年级");
        final String[] grades = getResources().getStringArray(R.array.grades);
        if(!progressDialog.isShowing())progressDialog.show();
        builder.setItems(grades, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String value = grades[which];
                submit("user_grade", grades[which], new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (progressDialog.isShowing()) progressDialog.dismiss();
                        try {
                            JSONObject object = new JSONObject(s);
                            if (object.getInt("result") == 200) {
                                gradeTv.setText(value);
                                userInfo.setGrade(value);
                                daoSession.update(userInfo);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Snackbar.make(toolbar, e.toString(), Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        builder.show();
    }

    @OnClick(R.id.insititude_ll)void setInsititude(){
        bundle.putString("title", "修改学院");
        bundle.putString("descript", "修改你的学院让你认识更多的朋友");
        bundle.putString("hint", insititudeTv.getText().toString());
        bundle.putBoolean("isNumber", false);
        bundle.putString("param", "user_xueyuan");
        intent.putExtras(bundle);
        MyInfoActivity.this.startActivityForResult(intent, 3);
    }

    @OnClick(R.id.phone_ll)void setPhone(){
        bundle.putString("title", "修改手机号");
        bundle.putString("descript", "修改你的手机号让大家方便联系你");
        bundle.putString("hint", phoneTv.getText().toString());
        bundle.putBoolean("isNumber", true);
        bundle.putString("param", "phone_num");
        intent.putExtras(bundle);
        MyInfoActivity.this.startActivity(intent);
        MyInfoActivity.this.startActivityForResult(intent, 2);
    }

    @OnClick(R.id.city_ll)void setCity(){
        bundle.putString("title", "修改城市");
        bundle.putString("descript", "修改你的城市让同城的伙伴嗨起来");
        bundle.putString("hint", cityTv.getText().toString());
        bundle.putBoolean("isNumber", false);
        bundle.putString("param", "user_city");
        intent.putExtras(bundle);
        MyInfoActivity.this.startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            refreshMyInfo();
        }
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        if(requestCode==ConstantValue.INTENT_TAKE_PHOTOS){
            if(photoPath!=null){
                PhotoUtil.cameraCropImageUri(this, Uri.fromFile(new File(photoPath)),1,1,300,300);
                Log.i("MyInfoActivity",photoPath);
            }
        }else if(requestCode==ConstantValue.INTENT_SELECT_PHOTOS){
            if(data!=null){
                Uri photoUri=data.getData();
                Log.i("MyInfoActivity",photoUri.toString());
                if(photoUri!=null)PhotoUtil.cameraCropImageUri(this,photoUri,1,1,300,300);
            }
        }else if(requestCode==ConstantValue.INTENT_AFTER_CROPPHOTO){
            String path=ConstantValue.CROP_TEMP_PATH;
            Bitmap b= BitmapFactory.decodeFile(path);
            if(b!=null){
                headImg.setImageBitmap(b);
                getQiniuToken(path);
            }
        }
    }


    void submit(final String params, final String value,Response.Listener<String> listener){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.USER_UPDATE_INFO, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Snackbar.make(toolbar,volleyError.toString(),Snackbar.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put(params, value);
                map.put("user_name", MyApplication.getInstance().getUserInfo().getUsername());
                return map;
            }
        };
        getVolleyQueue().add(stringRequest);
    }



    public void getQiniuToken(final String path){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.GET_QINIU_TOKEN, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject object=new JSONObject(s);
                    if(object.getInt("result")==200){
                        String up_token=object.getString("up_token");
                        String img_key=object.getString("img_key");
                        Log.i(TAG,"upToken--->"+up_token+"Key--->"+img_key);
                        updateImage(path,up_token,img_key);
                    }else{
                        toast(toolbar, object.getString("desc"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i(TAG, e.toString());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i(TAG, volleyError.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("user_name", MyApplication.getInstance().getUserInfo().getUsername());
                return map;
            }
        };
        getVolleyQueue().add(stringRequest);
    }

    public void updateImage(final String img_path,final String upToken,final String img_key){
        UploadManager uploadManager = new UploadManager();
        uploadManager.put(img_path, img_key, upToken, new UpCompletionHandler() {
            @Override
            public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
                Log.i("TAG--updateImage", s);
                Map<String,String> map=new HashMap<String, String>();
                map.put("user_name",MyApplication.getInstance().getUserInfo().getUsername());
                map.put("img_key",s);
                new MyStringRequest(Urls.UPDATE_USER_IMG, map, new InternetListener() {
                    @Override
                    public void success(JSONObject desc) {
                        try {
                            toast(toolbar,desc.getString("desc"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void error(String desc) {
                        toast(toolbar,desc);
                    }
                });


            }

        }, new UploadOptions(null, null, false, new UpProgressHandler() {
            @Override
            public void progress(String s, double v) {
            }
        },null));
    }
}
