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

    public final static String GET_ACTIVITY="http://activity.xtongtong.cn/query/index.php";

    public final static String GET_QINIU_TOKEN="http://user.xtongtong.cn/get_qiniu_token/index.php";
    public final static String GET_JOIN_ACTIVITY="http://api.xtongtong.cn/api/user_query_activity_join.php";
    public final static String GET_COLLECT_ACTIVITY="http://api.xtongtong.cn/api/user_query_activity_collect.php";
    public final static String GET_PUBLISH_ACTIVITY="http://api.xtongtong.cn/api/user_query_activity_release.php";
    public final static String GET_DYNAMIC_ACTIVITY="http://api.xtongtong.cn/api/user_query_activity_join_&_release.php";







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

    public final static String ACTIVITY_JOIN_IN_CANCLE="activity_join_in_cancel"; //取消参加活动
    public final static String ACTIVITY_COLLECT_CANCLE="activity_collect_cancel"; //取消收藏活动



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





}
