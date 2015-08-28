package com.hdu.tx.aschool.common.utils;

import android.os.Build;
import android.os.Environment;

/**
 * Created by Administrator on 2015/5/6.
 */
public class ConstantValue {

    public static final String PNG=".png";

    public static final boolean mIsKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

    public static final String SD_ROOTPATH = Environment.getExternalStorageDirectory().getPath()+ "/aschool/";

    public static final String PHOTO_PATH = SD_ROOTPATH+"photo/";


    public static final String CROP_TEMP_FILENAME = "crop.jpg";

    public static final String CROP_TEMP_PATH = PHOTO_PATH+CROP_TEMP_FILENAME;


    public static final String RESULT_CODE = "result";
    public static final String DESCRIBE = "desc";

    public static final int INTENT_TAKE_PHOTOS = 10;
    public static final int  INTENT_SELECT_PHOTOS= 11;
    public static final int INTENT_AFTER_TAKE_PHOTOS = 12;
    public static final int  INTENT_AFTER_SELECT_PHOTOS= 13;
    public static final int  INTENT_AFTER_CROPPHOTO= 14;


}
