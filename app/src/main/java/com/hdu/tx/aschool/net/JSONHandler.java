package com.hdu.tx.aschool.net;

import android.util.Log;

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

    private static final String TAG ="JSONHandler";

    public static List<ActInfo> json2ListAct(JSONObject json) {
        List<ActInfo> actInfos=new ArrayList<>();
        try {
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
                info.setHostimageUrl(infoObject.getString("host_head_pic"));
                info.setActId(infoObject.getString("act_id"));

                info.setIsCollect(false);
                info.setIsJoin(false);
                info.setIsHost(false);
                actInfos.add(info);
            }
        }catch (JSONException e){
            e.printStackTrace();
            Log.e(TAG+"1",e.toString());
        }
        return  actInfos;
    }


    public static UserInfo json2UserInfo(JSONObject json) {
        UserInfo userInfo=null;
        try {
            userInfo=new UserInfo();
            userInfo.setNickname(json.getString("nick_name"));
            userInfo.setUsername(json.getString("user_name"));
            userInfo.setHeadimg_url(json.getString("head_pic"));
            userInfo.setSex(json.getString("gender"));
            userInfo.setSchool(json.getString("user_school"));
            userInfo.setGrade(json.getString("user_grade"));
            userInfo.setInstitute(json.getString("user_xueyuan"));
            userInfo.setPhoneNumber(json.getString("phone_num"));
            userInfo.setAge(json.getString("user_age"));
            userInfo.setCity(json.getString("user_city"));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG+"2",e.toString());
        }
        return  userInfo;
    }
}
