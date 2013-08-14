package com.hodanet.sanre.common.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <pre>
 * SharedPreferences ������
 * </pre>
 */
public class SharedPreferencesUtil {

    private static final String SP_PARAM      = "sp_info";
    private static final String DEFAULT_VALUE = "";

    /**
     * ��SharedPreferences�����ü�ֵ��
     * 
     * @param context
     * @param name
     * @param value
     * @return
     */
    public static void putString(Context context, String name, String value) {
        if (StringUtil.isBlank(name) || StringUtil.isBlank(value)) {
            return;
        }

        SharedPreferences userInfo = context.getSharedPreferences(SP_PARAM, 0);
        userInfo.edit().putString(name, value).commit();
    }

    /**
     * ���ݲ�����ȡSharedPreferences�е�ֵ
     * 
     * @param context
     * @param name
     * @return
     */
    public static String getString(Context context, String name) {
        if (StringUtil.isBlank(name)) {
            return null;
        }

        SharedPreferences userInfo = context.getSharedPreferences(SP_PARAM, 0);
        return userInfo.getString(name, DEFAULT_VALUE);
    }

    /**
     * ���SharedPreferences�е�ֵ
     * 
     * @param context
     * @param name
     * @return
     */
    public static boolean removeString(Context context, String name) {
        if (StringUtil.isBlank(name)) {
            return false;
        }

        SharedPreferences userInfo = context.getSharedPreferences(SP_PARAM, 0);
        userInfo.edit().remove(name).commit();
        return true;
    }

}
