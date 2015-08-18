package com.hdu.tx.aschool.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/13.
 */
public class UserInfoEntity implements Serializable {
    private String username;
    /**
     * 用户权限
     * 0、游客  1、普通用户  2、学生认证用户  3、组织认证用户
     */
    private int userRight;
    private String sex;
    private String headimg_url;
    private String nickname;
    private boolean isBindPhone;
    private String phoneNumber;
    private boolean isBindSchool;
    private SchoolInfoEntity schoolInfo;

    public String getHeadimg_url() {
        return headimg_url;
    }

    public void setHeadimg_url(String headimg_url) {
        this.headimg_url = headimg_url;
    }

    public boolean isBindPhone() {
        return isBindPhone;
    }

    public void setIsBindPhone(boolean isBindPhone) {
        this.isBindPhone = isBindPhone;
    }

    public boolean isBindSchool() {
        return isBindSchool;
    }

    public void setIsBindSchool(boolean isBindSchool) {
        this.isBindSchool = isBindSchool;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public SchoolInfoEntity getSchoolInfo() {
        return schoolInfo;
    }

    public void setSchoolInfo(SchoolInfoEntity schoolInfo) {
        this.schoolInfo = schoolInfo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserRight() {
        return userRight;
    }

    public void setUserRight(int userRight) {
        this.userRight = userRight;
    }
}
