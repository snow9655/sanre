package com.hodanet.sanre.common.constant;

/**
 * <pre>
 * 通用常量定义
 * </pre>
 */
public class CommonConstant {

    public static final String VIRTUAL_ID             = "vid";
    public static final String USER_ID                = "uid";
    public static final String USER_STATUS            = "status";

    /** 操作结果-成功 1 */
    public static final int    RESULT_SUCCESS         = 0x01;
    /** 操作结果-失败 0 */
    public static final int    RESULT_FAIL            = 0x0;
    /** 操作-参数为空 */
    public static final int    RESULT_PARAM_EMPTY     = 0x11;
    /** 操作-用户名或密码错误 */
    public static final int    RESULT_PARAM_ERROR     = 0x12;
    /** 操作-用户名被占用 */
    public static final int    RESULT_PARAM_EXIST     = 0x13;
    /** 操作结果-网络异常 -1 */
    public static final int    RESULT_NETWORK_FAIL    = 0xffffffff;

    public static final int    RESULT_EXIST           = 0X09;

    public static final int    RESULT_GETFREEULR      = 0X10;

    /** 可见 */
    public static final int    VISIBILITY_VISIBLE     = 0;
    /** 不可见 */
    public static final int    VISIBILITY_INVISIBLE   = 4;
    /** 不可见，且不占用布局空间 */
    public static final int    VISIBILITY_GONE        = 8;

    /** 未登录状态 */
    public static final String STATUS_LOGIN           = "1";
    /** 未登录状态 */
    public static final String STATUS_UNLOGIN         = "0";

    /** 提示语 加载更多 */
    public static final String REMIND_TEXT_LOAD_MORE  = "加载更多";
    /** 提示语 已加载全部 */
    public static final String REMIND_TEXT_LOADED_ALL = "已加载全部";
    /** 挂号费 单位 */
    public static final String RES_TEXT_PRICE_UNIT    = "元";
    /** 短信号码 */
    public static final String SMS_PHONE_NUMBER       = "10659128";
    /** 产品提供商 */
    public static final String PRODUCT_PROVIDER       = "杭州鸿大网络发展有限公司";
    /** 客服电话 */
    public static final String SERVICE_PHONE          = "95105196";
    /** 公司网站 */
    public static final String COMPANY_URL            = "http://www.hodanet.com";

    /** 积分墙开关 0.关闭 1.开启 */
    public static int          ADWALL_ONOFF           = 0;
    public static int          ADWALL_DEFAULTSCORE    = 100;

    public static final int    RESULT_GETADWALL_ONOFF = 0X13;

    /** 积分墙广告id 测试id为 0f470c7647f9a7b0550d562638fcb577 */
    public static final String ADWALL_ADUNITID        = "69eb6350fbcea228dc3d817656e662b8";
}
