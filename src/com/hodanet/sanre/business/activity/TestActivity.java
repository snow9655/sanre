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
 * ����
 * 
 * </pre>
 */
public class TestActivity extends HodaActivity {

    private int                 BatteryN;                       // Ŀǰ����
    private int                 BatteryV;                       // ��ص�ѹ
    private double              BatteryT;                       // ����¶�
    private String              BatteryStatus;                  // ���״̬
    private String              BatteryTemp;                    // ���ʹ�����

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
        notification = new Notification(R.drawable.ic_notify_logo, "ͼ��ߵ�����", System.currentTimeMillis());
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        notification.contentView = new RemoteViews(getPackageName(), R.layout.notification);
        notification.contentView.setProgressBar(R.id.qb, 100, 0, false);
        Intent notificationIntent = new Intent(this, TestActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notification.contentIntent = contentIntent;

        // ע��һ��ϵͳ BroadcastReceiver����Ϊ���ʵ�ؼ���֮���������ֱ����AndroidManifest.xml��ע��
        registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        TV = (TextView) findViewById(R.id.TV);

    }

    public void showNotification() {
        nm.notify(notification_id, notification);
    }

    /* �����㲥������ */
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {

                                                   public void onReceive(Context context, Intent intent) {
                                                       String action = intent.getAction();
                                                       /*
                                                        * �����׽����action��ACTION_BATTERY_CHANGED��
                                                        * ������onBatteryInfoReceiver()
                                                        */
                                                       if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                                                           BatteryN = intent.getIntExtra("level", 0); // Ŀǰ����
                                                           BatteryV = intent.getIntExtra("voltage", 0); // ��ص�ѹ
                                                           BatteryT = intent.getIntExtra("temperature", 0); // ����¶�

                                                           switch (intent.getIntExtra("status",
                                                                                      BatteryManager.BATTERY_STATUS_UNKNOWN)) {
                                                               case BatteryManager.BATTERY_STATUS_CHARGING:
                                                                   BatteryStatus = "���״̬";
                                                                   break;
                                                               case BatteryManager.BATTERY_STATUS_DISCHARGING:
                                                                   BatteryStatus = "�ŵ�״̬";
                                                                   break;
                                                               case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                                                                   BatteryStatus = "δ���";
                                                                   break;
                                                               case BatteryManager.BATTERY_STATUS_FULL:
                                                                   BatteryStatus = "������";
                                                                   break;
                                                               case BatteryManager.BATTERY_STATUS_UNKNOWN:
                                                                   BatteryStatus = "δ֪��״̬";
                                                                   break;
                                                           }

                                                           switch (intent.getIntExtra("health",
                                                                                      BatteryManager.BATTERY_HEALTH_UNKNOWN)) {
                                                               case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                                                                   BatteryTemp = "δ֪����";
                                                                   break;
                                                               case BatteryManager.BATTERY_HEALTH_GOOD:
                                                                   BatteryTemp = "״̬����";
                                                                   break;
                                                               case BatteryManager.BATTERY_HEALTH_DEAD:
                                                                   BatteryTemp = "���û�е�";
                                                                   break;
                                                               case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                                                                   BatteryTemp = "��ص�ѹ����";
                                                                   break;
                                                               case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                                                                   BatteryTemp = "��ع���";
                                                                   break;
                                                           }
                                                           TV.setText("Ŀǰ����Ϊ" + BatteryN + "% --- " + BatteryStatus
                                                                      + "\n" + "��ѹΪ" + BatteryV + "mV --- "
                                                                      + BatteryTemp + "\n" + "�¶�Ϊ" + (BatteryT * 0.1)
                                                                      + "��");
                                                       }
                                                   }
                                               };
}
