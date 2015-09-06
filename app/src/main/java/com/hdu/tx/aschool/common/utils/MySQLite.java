package com.hdu.tx.aschool.common.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pualgo on 2015/9/5.
 */
public class MySQLite extends SQLiteOpenHelper {


    // 数据库名称
      public static final String DBNAME = "ActDataBase";
       // 数据库版本
       public static final int VERSION = 2;
    // 建表语句，大小写不敏感
        public static final String TABLE="ActTable";


//    private Long id;
//    private String hostname;
//    private String imageUrl;
//    private String hostimageUrl;
//    private String title;
//    private String actId;
//    private String aid;
//    private String time;
//    private String address;
//    private String describe;
//    private String hostId;
//    private Integer lookTimes;
//    private Integer collectTimes;
//    private Integer totalpeopel;
//    private Integer joinedpeopel;
//    private Boolean isCollect;
//    private Boolean isJoin;
//    private Boolean isHost;

       private static final String CREATETABLE = "create table " + TABLE +
        "(id int, hostname text, imageUrl char[50], hostimageUrl char[50],title text" +
               "actId text, aid text, time text, address string, describle char[500] , " +
               " hostId text , lookTimes text , collectTimes text , totalpeopel text, " +
               " joinedpeopel int , isCollect int ,isJoin int , isHost int)";



    public MySQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onCreate(db);

    }

}
