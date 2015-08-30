package com.hdu.tx.aschool.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.hdu.tx.aschool.dao.DaoMaster;
import com.hdu.tx.aschool.dao.DaoSession;
import com.hdu.tx.aschool.dao.UserInfo;

/**
 * Created by Administrator on 2015/5/22.
 */
public class MyApplication extends Application {

    private static MyApplication instance;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private UserInfo userInfo;
    private RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public static MyApplication getInstance() {
        return instance;
    }



    public DaoMaster getDaoMaster(){
        if(daoMaster==null){
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "Note", null);
            db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
        }
        return daoMaster;
    }

    public DaoSession getDaoSession(){
        if(daoSession==null){
            daoSession = getDaoMaster().newSession();
        }
        return daoSession;
    }


    public UserInfo getUserInfo(){
        if(userInfo==null){
            userInfo=getDaoSession().getUserInfoDao().load(1l);
        }
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo){
        this.userInfo=userInfo;
    }


    public RequestQueue getVolleyQueue(){
        if (queue == null) {
            queue = Volley.newRequestQueue(getApplicationContext());
        }
        return queue;
    }
}
