package com.hdu.greendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class LeaseDaoGrenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(6, "com.hdu.tx.aschool.dao");
        createUserDao(schema);
        createActiDao(schema);
       // new DaoGenerator().generateAll(schema, "D://andorid/hdu2014/ASchool/app/src/main/java");
        new DaoGenerator().generateAll(schema, "F://APP/androidApp/ASchool-master/app/src/main/java");
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
        userInfo.addStringProperty("sex");
        userInfo.addStringProperty("city");
        userInfo.addStringProperty("age");

        userInfo.implementsInterface("java.io.Serializable");
    }

    public static void createActiDao(Schema schema){
        Entity userInfo = schema.addEntity("ActInfo");
        userInfo.addIdProperty().autoincrement();

        userInfo.addStringProperty("hostname");
        userInfo.addStringProperty("imageUrl");
        userInfo.addStringProperty("title");


        userInfo.addStringProperty("time");
        userInfo.addStringProperty("address");
        userInfo.addStringProperty("describe");
        userInfo.addStringProperty("hostId");


        userInfo.addIntProperty("lookTimes");
        userInfo.addIntProperty("collectTimes");
        userInfo.addIntProperty("totalpeopel");
        userInfo.addIntProperty("joinedpeopel");

        userInfo.implementsInterface("java.io.Serializable");
    }
}
