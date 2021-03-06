package com.hdu.tx.aschool.net;

/**
 * Created by Administrator on 2015/8/21.
 */
public class Urls {


    public final static String NETURL="http://user.xtongtong.cn/get_qiniu_token/";

    /**
     * 主机名
     */
    public final static String HOSTNAME="http://user.xtongtong.cn/";

    public final static String QUERY="query/";

    public final static String INDEX="index.php";

    public final static String USER_QUERY=HOSTNAME+QUERY+INDEX;



    public final static String API_URL="http://api.xtongtong.cn/api/";
    public final static String PHP=".php";


    //活动接口
    public final static String ACTIVITY_PUBLISH="activity_release"; //发布活动
    public final static String ACTIVITY_QUERY_BYID="activity_query"; //活动查询
    public final static String ACTIVITY_QUERY_MUTI="activity_query_mult_avtivity"; //多个活动查询
    public final static String ACTIVITY_QUERY_LUNBO="activity_query_mult_avtivity_lunbo"; //轮播活动查询
    public final static String ACTIVITY_DYNAMIC="user_query_activity_join_&_release"; //查询个人活动资料
    public final static String ACTIVITY_JOIN_IN="activity_join_in"; //参加活动
    public final static String ACTIVITY_COLLECT="activity_collect"; //收藏活动
    public final static String GET_PUBLISH_ACTIVITY="user_query_activity_release";//查询用户发布活动
    public final static String GET_COLLECT_ACTIVITY="user_query_activity_collect";//查询用户收藏活动
    public final static String GET_DYNAMIC_ACTIVITY="user_query_activity_join_&_release";//查询用户查询和参与活动
    public final static String GET_JOIN_ACTIVITY="user_query_activity_join";

    public final static String ACTIVITY_JOIN_IN_CANCLE="activity_join_in_cancel"; //取消参加活动
    public final static String ACTIVITY_COLLECT_CANCLE="activity_collect_cancel"; //取消收藏活动
    public final static String ACTIVITY_BROWSE="activity_browse"; //用户浏览决赛





    //用户接口
    public final static String USER_REGIST="user_registered"; //用户注册
    public final static String USER_LOGIN="user_sign_in"; //用户登录
    public final static String USER_QUERY_BYUSERNAME="user_query_by_user_name"; //用户查询 通过用户名
    public final static String USER_QUERY_BYID="user_query_by_user_id"; //用户查询 通过用户id
    public final static String USER_UPDATE_INFO="user_update_info"; //用户信息修改
    public final static String USER_UPDATE_PASSWORD="user_update_password"; //用户密码修改
    public final static String USER_GET_UPTOKEN="user_get_up_token"; //用户上传图片获取upToken
    public final static String USER_UPLOAD_PHOTO="user_get_up_token_success"; //用户上传图片获取upToken
    public final static String USER_DELETE="user_delete"; //用户注销
    public final static String USER_GROUP_MEMBERS="user_query_group_member_info"; //用户注销
    public final static String USER_GET_VCODE="yuntongxun/sms"; //用户注销





}
