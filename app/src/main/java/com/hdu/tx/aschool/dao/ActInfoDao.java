package com.hdu.tx.aschool.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.hdu.tx.aschool.dao.ActInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ACT_INFO".
*/
public class ActInfoDao extends AbstractDao<ActInfo, Long> {

    public static final String TABLENAME = "ACT_INFO";

    /**
     * Properties of entity ActInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Hostname = new Property(1, String.class, "hostname", false, "HOSTNAME");
        public final static Property ImageUrl = new Property(2, String.class, "imageUrl", false, "IMAGE_URL");
        public final static Property HostimageUrl = new Property(3, String.class, "hostimageUrl", false, "HOSTIMAGE_URL");
        public final static Property Title = new Property(4, String.class, "title", false, "TITLE");
        public final static Property ActId = new Property(5, String.class, "actId", false, "ACT_ID");
        public final static Property Aid = new Property(6, String.class, "aid", false, "AID");
        public final static Property Group_id = new Property(7, String.class, "group_id", false, "GROUP_ID");
        public final static Property Time = new Property(8, String.class, "time", false, "TIME");
        public final static Property Address = new Property(9, String.class, "address", false, "ADDRESS");
        public final static Property Describe = new Property(10, String.class, "describe", false, "DESCRIBE");
        public final static Property HostId = new Property(11, String.class, "hostId", false, "HOST_ID");
        public final static Property LookTimes = new Property(12, Integer.class, "lookTimes", false, "LOOK_TIMES");
        public final static Property CollectTimes = new Property(13, Integer.class, "collectTimes", false, "COLLECT_TIMES");
        public final static Property Totalpeopel = new Property(14, Integer.class, "totalpeopel", false, "TOTALPEOPEL");
        public final static Property Joinedpeopel = new Property(15, Integer.class, "joinedpeopel", false, "JOINEDPEOPEL");
        public final static Property IsCollect = new Property(16, Boolean.class, "isCollect", false, "IS_COLLECT");
        public final static Property IsJoin = new Property(17, Boolean.class, "isJoin", false, "IS_JOIN");
        public final static Property IsHost = new Property(18, Boolean.class, "isHost", false, "IS_HOST");
    };


    public ActInfoDao(DaoConfig config) {
        super(config);
    }
    
    public ActInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ACT_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"HOSTNAME\" TEXT," + // 1: hostname
                "\"IMAGE_URL\" TEXT," + // 2: imageUrl
                "\"HOSTIMAGE_URL\" TEXT," + // 3: hostimageUrl
                "\"TITLE\" TEXT," + // 4: title
                "\"ACT_ID\" TEXT," + // 5: actId
                "\"AID\" TEXT," + // 6: aid
                "\"GROUP_ID\" TEXT," + // 7: group_id
                "\"TIME\" TEXT," + // 8: time
                "\"ADDRESS\" TEXT," + // 9: address
                "\"DESCRIBE\" TEXT," + // 10: describe
                "\"HOST_ID\" TEXT," + // 11: hostId
                "\"LOOK_TIMES\" INTEGER," + // 12: lookTimes
                "\"COLLECT_TIMES\" INTEGER," + // 13: collectTimes
                "\"TOTALPEOPEL\" INTEGER," + // 14: totalpeopel
                "\"JOINEDPEOPEL\" INTEGER," + // 15: joinedpeopel
                "\"IS_COLLECT\" INTEGER," + // 16: isCollect
                "\"IS_JOIN\" INTEGER," + // 17: isJoin
                "\"IS_HOST\" INTEGER);"); // 18: isHost
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ACT_INFO\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ActInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String hostname = entity.getHostname();
        if (hostname != null) {
            stmt.bindString(2, hostname);
        }
 
        String imageUrl = entity.getImageUrl();
        if (imageUrl != null) {
            stmt.bindString(3, imageUrl);
        }
 
        String hostimageUrl = entity.getHostimageUrl();
        if (hostimageUrl != null) {
            stmt.bindString(4, hostimageUrl);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(5, title);
        }
 
        String actId = entity.getActId();
        if (actId != null) {
            stmt.bindString(6, actId);
        }
 
        String aid = entity.getAid();
        if (aid != null) {
            stmt.bindString(7, aid);
        }
 
        String group_id = entity.getGroup_id();
        if (group_id != null) {
            stmt.bindString(8, group_id);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(9, time);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(10, address);
        }
 
        String describe = entity.getDescribe();
        if (describe != null) {
            stmt.bindString(11, describe);
        }
 
        String hostId = entity.getHostId();
        if (hostId != null) {
            stmt.bindString(12, hostId);
        }
 
        Integer lookTimes = entity.getLookTimes();
        if (lookTimes != null) {
            stmt.bindLong(13, lookTimes);
        }
 
        Integer collectTimes = entity.getCollectTimes();
        if (collectTimes != null) {
            stmt.bindLong(14, collectTimes);
        }
 
        Integer totalpeopel = entity.getTotalpeopel();
        if (totalpeopel != null) {
            stmt.bindLong(15, totalpeopel);
        }
 
        Integer joinedpeopel = entity.getJoinedpeopel();
        if (joinedpeopel != null) {
            stmt.bindLong(16, joinedpeopel);
        }
 
        Boolean isCollect = entity.getIsCollect();
        if (isCollect != null) {
            stmt.bindLong(17, isCollect ? 1L: 0L);
        }
 
        Boolean isJoin = entity.getIsJoin();
        if (isJoin != null) {
            stmt.bindLong(18, isJoin ? 1L: 0L);
        }
 
        Boolean isHost = entity.getIsHost();
        if (isHost != null) {
            stmt.bindLong(19, isHost ? 1L: 0L);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ActInfo readEntity(Cursor cursor, int offset) {
        ActInfo entity = new ActInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // hostname
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // imageUrl
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // hostimageUrl
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // title
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // actId
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // aid
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // group_id
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // time
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // address
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // describe
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // hostId
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // lookTimes
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13), // collectTimes
            cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14), // totalpeopel
            cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15), // joinedpeopel
            cursor.isNull(offset + 16) ? null : cursor.getShort(offset + 16) != 0, // isCollect
            cursor.isNull(offset + 17) ? null : cursor.getShort(offset + 17) != 0, // isJoin
            cursor.isNull(offset + 18) ? null : cursor.getShort(offset + 18) != 0 // isHost
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ActInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setHostname(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setImageUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setHostimageUrl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTitle(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setActId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAid(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setGroup_id(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAddress(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setDescribe(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setHostId(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setLookTimes(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setCollectTimes(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
        entity.setTotalpeopel(cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14));
        entity.setJoinedpeopel(cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15));
        entity.setIsCollect(cursor.isNull(offset + 16) ? null : cursor.getShort(offset + 16) != 0);
        entity.setIsJoin(cursor.isNull(offset + 17) ? null : cursor.getShort(offset + 17) != 0);
        entity.setIsHost(cursor.isNull(offset + 18) ? null : cursor.getShort(offset + 18) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ActInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ActInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
