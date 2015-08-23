package com.hdu.greendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class LeaseDaoGrenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.hdu.tx.aschool.dao");
        createUserDao(schema);
        new DaoGenerator().generateAll(schema, "D://andorid/hdu2014/ASchool/app/src/main/java");
    }

    public static void  createUserDao(Schema schema){
        Entity userInfo = schema.addEntity("UserInfo");
        userInfo.addIdProperty();
        userInfo.addStringProperty("username");
        userInfo.addStringProperty("nickname");
        userInfo.addIntProperty("level");
        userInfo.addStringProperty("school");
        userInfo.addStringProperty("major");
        userInfo.addStringProperty("headimg_url");
        userInfo.addStringProperty("phoneNumber");
        userInfo.addBooleanProperty("isBindPhone");
        userInfo.addStringProperty("grade");
        userInfo.addStringProperty("institute");

    }
}
