package com.hdu.tx.aschool.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.easemob.EMCallBack;
import com.hdu.tx.aschool.R;
import com.hdu.tx.aschool.dao.DaoMaster;
import com.hdu.tx.aschool.dao.DaoSession;
import com.hdu.tx.aschool.dao.UserInfo;
import com.hdu.tx.aschool.easemod.applib.DemoHXSDKHelper;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

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
    private DisplayImageOptions options;

    public static DemoHXSDKHelper hxSDKHelper = new DemoHXSDKHelper();

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        hxSDKHelper.onInit(this);
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


    private void initImageLoader() {
        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils
                .getOwnCacheDirectory(this,
                        "imageloader/Cache");

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))
                .memoryCacheSize(12 * 1024 * 1024)
                .discCacheSize(32 * 1024 * 1024).discCacheFileCount(100)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.top_banner_android)
                .showImageForEmptyUri(R.drawable.top_banner_android)
                .showImageOnFail(R.drawable.top_banner_android)
                .cacheInMemory(true).cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY).build();

    }

    public  void loadImage(String url,ImageView view){
        ImageLoader.getInstance().displayImage(url, view,
                options);
    }


    /**
     * 退出登录,清空数据
     */
    public void logout(final boolean isGCM,final EMCallBack emCallBack) {
        // 先调用sdk logout，在清理app中自己的数据
        hxSDKHelper.logout(isGCM, emCallBack);
    }

    public void login(String user,String pass){
        hxSDKHelper.setHXId(user);
        hxSDKHelper.setPassword(pass);
    }
}
