package com.hdu.tx.aschool.net;

import com.hdu.tx.aschool.dao.ActInfo;
import com.hdu.tx.aschool.dao.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenglin on 2015/8/30.
 */
public class JSONHandler {

    public static List<ActInfo> json2ListAct(JSONObject json) throws JSONException{
        List<ActInfo> actInfos=new ArrayList<>();
        JSONArray array = json.getJSONArray("activities");
        for (int i = 0; i <array.length() ; i++) {
            JSONObject infoObject=array.getJSONObject(i);
            ActInfo info=new ActInfo();
            info.setTitle(infoObject.getString("title"));
            info.setTime(infoObject.getString("start_time"));
            info.setTotalpeopel(infoObject.getInt("act_num"));
            info.setAddress(infoObject.getString("act_place"));
            info.setDescribe(infoObject.getString("content"));
            info.setHostname(infoObject.getString("host_name"));
            info.setHostId(infoObject.getString("host_id"));
            info.setJoinedpeopel(infoObject.getInt("join_num"));
            info.setCollectTimes(infoObject.getInt("collect_num"));
            info.setLookTimes(infoObject.getInt("browse_num"));
            info.setImageUrl(infoObject.getString("act_img"));
            actInfos.add(info);
        }
        return  actInfos;
    }


    public static UserInfo json2UserInfo(JSONObject json) throws JSONException{
        UserInfo userInfo=new UserInfo();
        userInfo.setNickname(json.getString("nickname"));
        userInfo.setUsername(json.getString("username"));
        userInfo.setHeadimg_url(json.getString("headpic"));
        userInfo.setSex(json.getString("gender"));
        userInfo.setSchool(json.getString("user_school"));
        userInfo.setGrade(json.getString("user_grade"));
        userInfo.setInstitute(json.getString("user_xueyuan"));
        userInfo.setPhoneNumber(json.getString("phone_num"));
        userInfo.setAge(json.getString("user_age"));
        userInfo.setCity(json.getString("user_city"));
        return  userInfo;
    }
}
