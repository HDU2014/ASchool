package com.hdu.tx.aschool.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/18.
 */
public class SchoolInfoEntity implements Serializable{
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 专业名称
     */
    private String major;
    /**
     * 年级
     */
    private String grade;
    /**
     * 学院名称
     */
    private String institute;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }



}
