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
 * ���ü�鹤����
 * </pre>
 */
public class CheckUtil {

    public static String        APN_URI            = "content://telephony/carriers"; // APN�� uri
    public static int           INIT_APN           = 0;                             // ԭ�����ӵ�APN Id
    public static String        DX_PROXY_ID        = "10.0.0.200";                  // ����ĵ�ַ���˿� 80
    private static final String TelecomPhonePrefix = "133|153|180|189";

    public static String getSDPath() {
        File sdDir = null;
        // �ж�sd���Ƿ����
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            // ���SD�����ڣ����ȡ��Ŀ¼
            sdDir = Environment.getExternalStorageDirectory();
            return sdDir.toString();
        }
        return "";
    }

    /**
     * ���sdcard�Ƿ����
     * 
     * @return ���ڷ���true;���򷵻�false.
     */
    public static boolean checkSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * �Ƿ�������
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
     * ��ȡ����״̬����:���߾�����Ϊ1��CTNETΪ2��CTWAPΪ3
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
            // ��WIFI����
            Uri uri = Uri.parse(APN_URI);// preferapn
            ContentResolver contr = ctx.getContentResolver();
            Cursor cursor = contr.query(uri.withAppendedPath(uri, "preferapn"), null, null, null, null);// ��ѯ��ǰ��apn ��id
            if (cursor != null && cursor.moveToFirst()) {
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                INIT_APN = _id;
                cursor.close();
            }
            int id = -1;
            String apn = null;
            Cursor cursor1 = contr.query(uri, null, null, null, null);// ����wap��id
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
                if (id == -1) {// û�в�������
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
     * �Ƿ��ѿ�������ģʽ
     */
    public static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) != 0;
    }

    /**
     * У������Ƿ���ź����
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
