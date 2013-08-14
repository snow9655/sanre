package com.hodanet.sanre.business.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RemoteViews;
import com.hodanet.sanre.R;
import com.hodanet.sanre.common.base.HodaActivity;

/**
 * <pre>
 * 测试
 * 
 * </pre>
 */
public class TestActivity extends HodaActivity {

    private int                 BatteryN;                       // 目前电量
    private int                 BatteryV;                       // 电池电压
    private double              BatteryT;                       // 电池温度
    private String              BatteryStatus;                  // 电池状态
    private String              BatteryTemp;                    // 电池使用情况

    private int                 notification_id = 19172439;
    private NotificationManager nm;
    private Handler             handler         = new Handler();
    private Notification        notification;
    private Button              btn_start;
    private Button              btn_stop;
    public TextView             TV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        btn_start = (Button) this.findViewById(R.id.start);
        btn_start.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showNotification();
            }
        });
        btn_stop = (Button) this.findViewById(R.id.stop);
        btn_stop.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                nm.cancel(notification_id);
            }
        });

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification = new Notification(R.drawable.ic_notify_logo, "图标边的文字", System.currentTimeMillis());
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        notification.contentView = new RemoteViews(getPackageName(), R.layout.notification);
        notification.contentView.setProgressBar(R.id.qb, 100, 0, false);
        Intent notificationIntent = new Intent(this, TestActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notification.contentIntent = contentIntent;

        // 注册一个系统 BroadcastReceiver，作为访问电池计量之用这个不能直接在AndroidManifest.xml中注册
        registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        TV = (TextView) findViewById(R.id.TV);

    }

    public void showNotification() {
        nm.notify(notification_id, notification);
    }

    /* 创建广播接收器 */
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {

                                                   public void onReceive(Context context, Intent intent) {
                                                       String action = intent.getAction();
                                                       /*
                                                        * 如果捕捉到的action是ACTION_BATTERY_CHANGED，
                                                        * 就运行onBatteryInfoReceiver()
                                                        */
                                                       if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                                                           BatteryN = intent.getIntExtra("level", 0); // 目前电量
                                                           BatteryV = intent.getIntExtra("voltage", 0); // 电池电压
                                                           BatteryT = intent.getIntExtra("temperature", 0); // 电池温度

                                                           switch (intent.getIntExtra("status",
                                                                                      BatteryManager.BATTERY_STATUS_UNKNOWN)) {
                                                               case BatteryManager.BATTERY_STATUS_CHARGING:
                                                                   BatteryStatus = "充电状态";
                                                                   break;
                                                               case BatteryManager.BATTERY_STATUS_DISCHARGING:
                                                                   BatteryStatus = "放电状态";
                                                                   break;
                                                               case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                                                                   BatteryStatus = "未充电";
                                                                   break;
                                                               case BatteryManager.BATTERY_STATUS_FULL:
                                                                   BatteryStatus = "充满电";
                                                                   break;
                                                               case BatteryManager.BATTERY_STATUS_UNKNOWN:
                                                                   BatteryStatus = "未知道状态";
                                                                   break;
                                                           }

                                                           switch (intent.getIntExtra("health",
                                                                                      BatteryManager.BATTERY_HEALTH_UNKNOWN)) {
                                                               case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                                                                   BatteryTemp = "未知错误";
                                                                   break;
                                                               case BatteryManager.BATTERY_HEALTH_GOOD:
                                                                   BatteryTemp = "状态良好";
                                                                   break;
                                                               case BatteryManager.BATTERY_HEALTH_DEAD:
                                                                   BatteryTemp = "电池没有电";
                                                                   break;
                                                               case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                                                                   BatteryTemp = "电池电压过高";
                                                                   break;
                                                               case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                                                                   BatteryTemp = "电池过热";
                                                                   break;
                                                           }
                                                           TV.setText("目前电量为" + BatteryN + "% --- " + BatteryStatus
                                                                      + "\n" + "电压为" + BatteryV + "mV --- "
                                                                      + BatteryTemp + "\n" + "温度为" + (BatteryT * 0.1)
                                                                      + "℃");
                                                       }
                                                   }
                                               };
}
