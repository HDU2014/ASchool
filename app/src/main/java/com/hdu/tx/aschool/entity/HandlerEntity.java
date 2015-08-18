package com.hdu.tx.aschool.entity;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2015/8/18.
 */
public class HandlerEntity {

    private HandlerEntity() {

    }


    private static HandlerEntity instance = null;
    /**
     * 懒汉式单例模式，使用双重检查锁定的方式保证线程安全，
     * @return HandlerEntity单例对象
     */
    public static HandlerEntity getInstance() {
        if (instance == null) {
            synchronized (HandlerEntity.class) {
                if (instance == null) {
                    instance = new HandlerEntity();
                }
            }
        }
        return instance;
    }

    private void saveObject(Context context,String name,Object sod){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(name,context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(sod);
        } catch (Exception e) {
            e.printStackTrace();
            //这里是保存文件产生异常
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    //fos流关闭异常
                    e.printStackTrace();
                }
            }
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    //oos流关闭异常
                    e.printStackTrace();
                }
            }
        }
    }


}
