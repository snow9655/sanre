package com.hodanet.sanre.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.hodanet.sanre.common.util.ActivityManager;
import com.umeng.analytics.MobclickAgent;

/**
 * <pre>
 * ������չ
 * </pre>
 */
public class HodaActivity extends Activity {

    public int ScreenWidth;
    public int ScreenHeight;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        ScreenWidth = dm.widthPixels;
        ScreenHeight = dm.heightPixels;
    }

    public void onResume() {
        super.onResume();
        // ����ͳ��
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        // ����ͳ��
        MobclickAgent.onPause(this);
    }

}
