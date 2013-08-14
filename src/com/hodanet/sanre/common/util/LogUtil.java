package com.hodanet.sanre.common.util;

import android.util.Log;

/**
 * <pre>
 * 日志工具类
 * </pre>
 */
public class LogUtil {

    private static boolean isTest = true; // 测试环境

    // private static boolean isTest = false; // 正式环境

    public static void v(String tag, String msg) {
        if (isTest) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isTest) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isTest) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isTest) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isTest) {
            Log.e(tag, msg);
        }
    }
}
