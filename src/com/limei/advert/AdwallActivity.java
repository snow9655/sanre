package com.limei.advert;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.hodanet.ad.ImmobView;
import com.hodanet.ad.LMAdListener;
import com.hodanet.sanre.R;
import com.hodanet.sanre.common.constant.CommonConstant;

public class AdwallActivity extends Activity implements LMAdListener {

    private String       tag      = "AdwallActivity";
    private ImmobView    view     = null;
    public static String adUnitID = CommonConstant.ADWALL_ADUNITID; // 这里是广告墙的广告位id
    private LinearLayout layout   = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(tag, "onCreate");
        setContentView(R.layout.advert_wall);
        layout = (LinearLayout) findViewById(R.id.advert_wall_layout);
        Context context = this;
        // 特定市场渠道id设置
        //Hashtable<String, String> hashtable=new Hashtable();
        //hashtable.put("channelID", "60001");
        view = new ImmobView(context, adUnitID);
        //view.setUserInfo(hashtable);
        // 设置布局文件
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                                                                         LinearLayout.LayoutParams.FILL_PARENT);

        view.setLayoutParams(params);
        // 增加webview
        view.setAdListener(this);
        layout.addView(view);
        layout.setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(tag, "onDestroy");
        if (view != null) {
            view.destroy();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(tag, "onPause");
        // 调用此方法，后台广告停止请求广告
        view.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(tag, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(tag, "onResume");
        // 调用此方法，后台广告恢复请求广告
        view.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(tag, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(tag, "onStop");
    }

    /**
     * This method will be called when a single ad request performed successfully.
     */

    @Override
    public void onAdReceived(ImmobView arg0) {
        Log.i(tag, "onAdReceived");
        // TODO Auto-generated method stub
        if (view != null) {
            view.display();
        }
    }

    /**
     * This method will be called when some error
     * 
     * @param eCode
     */
    @Override
    public void onDismissScreen(ImmobView arg0) {
        // TODO Auto-generated method stub
        Log.i(tag, "onDismissScreen");
        onKeyDown(KeyEvent.KEYCODE_BACK, null);
    }

    /**
     * Called when an ad is clicked and about to return to the application.</br> 当（全屏）广告被点击或者被关闭，将要返回返回主程序见面时被调用。
     */
    @Override
    public void onFailedToReceiveAd(ImmobView arg0, int arg1) {
        // TODO Auto-generated method stub
        Log.i(tag, "onFailedToReceiveAd");
    }

    /**
     * Called when an ad is clicked and going to start a new Activity that will leave the application</br>
     * 当广告调用一个新的activity并且会导致离开目前运行程序时被调用。如：调用本地地图程序。
     */
    @Override
    public void onLeaveApplication(ImmobView arg0) {
        // TODO Auto-generated method stub
        Log.i(tag, "onLeaveApplication");
    }

    /**
     * Called when an Activity is created in front of the app.</br> 当广告activity被撞见并且显示在覆盖在屏幕上面时调用本方法。
     */
    @Override
    public void onPresentScreen(ImmobView arg0) {
        // TODO Auto-generated method stub
        Log.i(tag, "onPresentScreen");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
