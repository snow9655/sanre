package com.hodanet.sanre.common.util;

import java.io.File;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;

import com.hodanet.sanre.common.constant.NetTypeConstant;

/**
 * <pre>
 * 常用检查工具类
 * </pre>
 */
public class CheckUtil {

    public static String        APN_URI            = "content://telephony/carriers"; // APN的 uri
    public static int           INIT_APN           = 0;                             // 原先连接的APN Id
    public static String        DX_PROXY_ID        = "10.0.0.200";                  // 代理的地址，端口 80
    private static final String TelecomPhonePrefix = "133|153|180|189";

    public static String getSDPath() {
        File sdDir = null;
        // 判断sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            // 如果SD卡存在，则获取根目录
            sdDir = Environment.getExternalStorageDirectory();
            return sdDir.toString();
        }
        return "";
    }

    /**
     * 检测sdcard是否存在
     * 
     * @return 存在返回true;否则返回false.
     */
    public static boolean checkSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否有网络
     * 
     * @param context
     */
    public static boolean checkNetwork(Context context) {
        boolean flag = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm.getActiveNetworkInfo() != null) {
                flag = cm.getActiveNetworkInfo().isAvailable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 获取网络状态类型:无线局域网为1，CTNET为2，CTWAP为3
     */
    public static int CheckNetState(Context ctx) {
        NetworkInfo netInf = null;
        try {
            ConnectivityManager connManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            netInf = connManager.getActiveNetworkInfo();
            if (netInf == null || !netInf.isAvailable()) return NetTypeConstant.NET_TYPE_UNKONW_ID;
        } catch (Exception e) {
            e.printStackTrace();
            return NetTypeConstant.NET_TYPE_UNKONW_ID;
        }
        if (netInf != null && NetTypeConstant.NET_TYPE_WIFI.equals(netInf.getTypeName())) {
            return NetTypeConstant.NET_TYPE_WIFI_ID;
        } else {
            // 非WIFI联网
            Uri uri = Uri.parse(APN_URI);// preferapn
            ContentResolver contr = ctx.getContentResolver();
            Cursor cursor = contr.query(uri.withAppendedPath(uri, "preferapn"), null, null, null, null);// 查询当前的apn 的id
            if (cursor != null && cursor.moveToFirst()) {
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                INIT_APN = _id;
                cursor.close();
            }
            int id = -1;
            String apn = null;
            Cursor cursor1 = contr.query(uri, null, null, null, null);// 遍历wap的id
            if (cursor1 == null) return NetTypeConstant.NET_TYPE_UNKONW_ID;
            else {
                while (cursor1.moveToNext()) {
                    id = cursor1.getInt(cursor1.getColumnIndex("_id"));
                    apn = cursor1.getString(cursor1.getColumnIndex("proxy"));
                    String name = cursor1.getString(cursor1.getColumnIndex("name"));
                    if (DX_PROXY_ID.equals(apn)) {
                        break;
                    }
                }
                cursor1.close();
                if (id == -1) {// 没有彩显网关
                    return NetTypeConstant.NET_TYPE_UNKONW_ID;
                } else if (INIT_APN != id) {
                    return NetTypeConstant.NET_TYPE_CTNET_ID;
                } else {
                    return NetTypeConstant.NET_TYPE_CTWAP_ID;
                }
            }
        }
    }

    /**
     * 是否已开启飞行模式
     */
    public static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) != 0;
    }

    /**
     * 校验号码是否电信号码段
     */
    public static boolean validateTelecomPhone(String phone) {
        if (StringUtil.isBlank(phone)) {
            return false;
        }

        if (phone.length() != 11) {
            return false;
        }

        String prefix = StringUtil.substring(phone, 0, 3);
        if (StringUtil.indexOf(TelecomPhonePrefix, prefix) < 0) {
            return false;
        }

        return true;
    }

}
