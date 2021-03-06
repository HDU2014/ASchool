package com.hdu.tx.aschool.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "USER_INFO".
 */
public class UserInfo implements java.io.Serializable {

    private Long id;
    private Integer loadTimes;
    private String username;
    private String nickname;
    private Integer level;
    private String school;
    private String major;
    private String headimg_url;
    private String phoneNumber;
    private Boolean isBindPhone;
    private String grade;
    private String institute;
    private String sex;
    private String city;
    private String age;
    private String interestTabs;

    public UserInfo() {
    }

    public UserInfo(Long id) {
        this.id = id;
    }

    public UserInfo(Long id, Integer loadTimes, String username, String nickname, Integer level, String school, String major, String headimg_url, String phoneNumber, Boolean isBindPhone, String grade, String institute, String sex, String city, String age, String interestTabs) {
        this.id = id;
        this.loadTimes = loadTimes;
        this.username = username;
        this.nickname = nickname;
        this.level = level;
        this.school = school;
        this.major = major;
        this.headimg_url = headimg_url;
        this.phoneNumber = phoneNumber;
        this.isBindPhone = isBindPhone;
        this.grade = grade;
        this.institute = institute;
        this.sex = sex;
        this.city = city;
        this.age = age;
        this.interestTabs = interestTabs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLoadTimes() {
        return loadTimes;
    }

    public void setLoadTimes(Integer loadTimes) {
        this.loadTimes = loadTimes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getHeadimg_url() {
        return headimg_url;
    }

    public void setHeadimg_url(String headimg_url) {
        this.headimg_url = headimg_url;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getIsBindPhone() {
        return isBindPhone;
    }

    public void setIsBindPhone(Boolean isBindPhone) {
        this.isBindPhone = isBindPhone;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getInterestTabs() {
        return interestTabs;
    }

    public void setInterestTabs(String interestTabs) {
        this.interestTabs = interestTabs;
    }

}
