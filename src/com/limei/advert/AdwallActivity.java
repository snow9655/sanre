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
    public static String adUnitID = CommonConstant.ADWALL_ADUNITID; // �����ǹ��ǽ�Ĺ��λid
    private LinearLayout layout   = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(tag, "onCreate");
        setContentView(R.layout.advert_wall);
        layout = (LinearLayout) findViewById(R.id.advert_wall_layout);
        Context context = this;
        // �ض��г�����id����
        //Hashtable<String, String> hashtable=new Hashtable();
        //hashtable.put("channelID", "60001");
        view = new ImmobView(context, adUnitID);
        //view.setUserInfo(hashtable);
        // ���ò����ļ�
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                                                                         LinearLayout.LayoutParams.FILL_PARENT);

        view.setLayoutParams(params);
        // ����webview
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
        // ���ô˷�������̨���ֹͣ������
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
        // ���ô˷�������̨���ָ�������
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
     * Called when an ad is clicked and about to return to the application.</br> ����ȫ������汻������߱��رգ���Ҫ���ط������������ʱ�����á�
     */
    @Override
    public void onFailedToReceiveAd(ImmobView arg0, int arg1) {
        // TODO Auto-generated method stub
        Log.i(tag, "onFailedToReceiveAd");
    }

    /**
     * Called when an ad is clicked and going to start a new Activity that will leave the application</br>
     * ��������һ���µ�activity���һᵼ���뿪Ŀǰ���г���ʱ�����á��磺���ñ��ص�ͼ����
     */
    @Override
    public void onLeaveApplication(ImmobView arg0) {
        // TODO Auto-generated method stub
        Log.i(tag, "onLeaveApplication");
    }

    /**
     * Called when an Activity is created in front of the app.</br> �����activity��ײ��������ʾ�ڸ�������Ļ����ʱ���ñ�������
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
