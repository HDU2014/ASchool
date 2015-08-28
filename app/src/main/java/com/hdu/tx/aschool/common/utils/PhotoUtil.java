package com.hdu.tx.aschool.common.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import com.hdu.tx.aschool.base.BaseActivity;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2015/6/21.
 */
public class PhotoUtil {

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static Bitmap getFitDegreeBitmap(String path){
        Bitmap b1= BitmapFactory.decodeFile(path);
        int degree= PhotoUtil.readPictureDegree(path);
        Matrix matrix=new Matrix();
        matrix.postRotate(degree);
        Bitmap bitmap=Bitmap.createBitmap(b1,0,0,b1.getWidth(),b1.getHeight(),matrix,false);
        return bitmap;
    }


    public static  void cameraCropImageUri(Activity activity,Uri uri,int aspectX,int ascpectY, int width,int height) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/jpeg");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", ascpectY);
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("scale",true);
        intent.putExtra("return-data", false);
        File file=new File(ConstantValue.PHOTO_PATH);
        if(!file.exists()) {
            file.mkdirs();
        }
        File photofile=new File(ConstantValue.CROP_TEMP_PATH);
        if(photofile.exists()) try {
            photofile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photofile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, ConstantValue.INTENT_AFTER_CROPPHOTO);
    }


    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>100) {	//循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片


        return bitmap;
    }


    public static String startIntentTakePhotos(BaseActivity activity){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            String dir = ConstantValue.PHOTO_PATH;
            File destDir = new File(dir);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            File file = new File(dir, System.currentTimeMillis() + ".jpg");
            String path = file.getAbsolutePath();
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            try {
                activity.startActivityForResult(intent, ConstantValue.INTENT_TAKE_PHOTOS);
                return path;
            } catch (ActivityNotFoundException anf) {
                //Activity not found
                return null;
            }
        } else {
            //SD not exist
            return null;
        }
    }


    public static void startSelectImageFromLocal(BaseActivity activity) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Intent intent;
            if (Build.VERSION.SDK_INT < 19) {
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
            } else {
                intent = new Intent( Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            }
            try {
                activity.startActivityForResult(intent, ConstantValue.INTENT_SELECT_PHOTOS);
            } catch (ActivityNotFoundException anf) {

            }
        } else {

        }
    }


}
