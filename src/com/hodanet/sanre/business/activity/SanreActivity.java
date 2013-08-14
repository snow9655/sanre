package com.hodanet.sanre.business.activity;

import java.text.DecimalFormat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hodanet.sanre.R;
import com.hodanet.sanre.business.logic.SanreLogicService;
import com.hodanet.sanre.business.model.TemperatureModel;
import com.hodanet.sanre.common.base.HodaActivity;

/**
 * <pre>
 * 
 * 
 * </pre>
 */
public class SanreActivity extends HodaActivity {

    private LinearLayout      temperaturebar;
    private LinearLayout      btn_sanre;
    private LinearLayout      title_bg;
    private Animation         animation;
    private Handler           handler;
    private Handler           progress_handler   = new Handler();
    private ProgressBar       mProgressBar;

    private TextView          temperature;
    private TextView          mProgressBar_text;
    private TextView          btn_sanre_text;
    private TextView          sanre_title;

    private double            BatteryT;
    private DecimalFormat     df                 = new DecimalFormat(".##");
    private SanreLogicService mSanreLogicService = SanreLogicService.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sanre);
        initview();
        registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    private void initview() {
        temperaturebar = (LinearLayout) this.findViewById(R.id.temperature_bar);
        temperature = (TextView) this.findViewById(R.id.sanre_temperature);
        mProgressBar = (ProgressBar) this.findViewById(R.id.sanre_progressbar);
        mProgressBar_text = (TextView) this.findViewById(R.id.sanre_progressbar_text);
        btn_sanre_text = (TextView) this.findViewById(R.id.sanre_button_text);
        sanre_title = (TextView) this.findViewById(R.id.sanre_title);
        title_bg = (LinearLayout) this.findViewById(R.id.sanre_title_bg);
        btn_sanre = (LinearLayout) this.findViewById(R.id.sanre_button);
        btn_sanre.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                 * float tem = dipTopx(242); float a = (float) (tem * 0.25); initanimation(0, a);
                 * temperaturebar.startAnimation(animation);
                 */
                btn_sanre.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar_text.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(0);
                progress_handler.post(progress);
            }
        });
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    temperaturebar.setBackgroundResource(R.drawable.temperature_bar_bule);
                    title_bg.setBackgroundResource(R.drawable.sanre_top_blue_bg);
                    sanre_title.setText("您的手机现在很凉快 ：）");
                }
            }

        };
    }

    private void initanimation(float fromy, float toy) {
        animation = new TranslateAnimation(0.0f, 0.0f, fromy, toy);
        animation.setDuration(1000);
        animation.setFillAfter(true);
    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {

                                                   @Override
                                                   public void onReceive(Context context, Intent intent) {
                                                       String action = intent.getAction();
                                                       if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                                                           BatteryT = intent.getIntExtra("temperature", 0) * 0.1;
                                                           title_bg.setBackgroundResource(R.drawable.sanre_top_bg);
                                                           sanre_title.setText("您的手机急需散热 ！");
                                                           temperaturebar.setBackgroundResource(R.drawable.temperature_bar);
                                                           if (BatteryT > 40.0) {
                                                               BatteryT = BatteryT * 1.2;

                                                           } else {
                                                               TemperatureModel model = mSanreLogicService.getTemperature();
                                                               if (model == null) {
                                                                   BatteryT = BatteryT * 1.2;
                                                               } else {
                                                                   if (System.currentTimeMillis() - model.getTime() > 1200000) {
                                                                       BatteryT = BatteryT * 1.2;

                                                                   } else {
                                                                       if (BatteryT > Double.parseDouble(model.getNtemp()) * 1.2) {
                                                                           BatteryT = BatteryT * 1.2;

                                                                       } else {
                                                                           title_bg.setBackgroundResource(R.drawable.sanre_top_blue_bg);
                                                                           sanre_title.setText("您的手机现在很凉快 ：）");
                                                                           temperaturebar.setBackgroundResource(R.drawable.temperature_bar_bule);
                                                                       }
                                                                   }
                                                               }
                                                           }

                                                           temperature.setText(df.format(BatteryT) + "℃");
                                                           float tem = dipTopx(242);
                                                           float b = (float) (BatteryT * 0.1 - 10);
                                                           float a = (float) b / 40;
                                                           float c = (float) (tem * (1 - a));
                                                           initanimation(tem, c);
                                                           temperaturebar.startAnimation(animation);
                                                       }
                                                   }
                                               };

    private Runnable          progress         = new Runnable() {

                                                   @Override
                                                   public void run() {
                                                       if (mProgressBar.getProgress() == 200) {
                                                           progress_handler.removeCallbacks(progress);
                                                           btn_sanre.setVisibility(View.VISIBLE);

                                                           mProgressBar.setVisibility(View.GONE);
                                                           mProgressBar_text.setVisibility(View.GONE);
                                                           btn_sanre_text.setText("降温完成");
                                                           double oldtem = BatteryT;
                                                           double newtem = oldtem * 0.9;
                                                           temperature.setText(df.format(newtem * 0.1) + "℃");
                                                           float tem = dipTopx(242);
                                                           initanimation(tem, (float) (1 - (newtem * 0.1 - 10) / 40)
                                                                              * tem);
                                                           temperaturebar.startAnimation(animation);
                                                           changecolor();
                                                           TemperatureModel model = new TemperatureModel();
                                                           model.setHtemp(String.valueOf(oldtem * 1.2));
                                                           model.setLtemp(String.valueOf(oldtem * 0.9));
                                                           model.setNtemp(String.valueOf(oldtem));
                                                           mSanreLogicService.AddTempeareture(model);
                                                       } else {
                                                           if (mProgressBar.getProgress() > 50) {
                                                               mProgressBar_text.setText("正在智能降温...");
                                                           }
                                                           if (mProgressBar.getProgress() > 120) {
                                                               mProgressBar_text.setText("正在智能调节电池...");
                                                           }
                                                           if (mProgressBar.getProgress() > 50
                                                               && mProgressBar.getProgress() < 90) {
                                                               mProgressBar.setProgress(mProgressBar.getProgress() + 1);
                                                           } else if (mProgressBar.getProgress() > 140
                                                                      && mProgressBar.getProgress() < 170) {
                                                               mProgressBar.setProgress(mProgressBar.getProgress() + 1);
                                                           } else {
                                                               mProgressBar.setProgress(mProgressBar.getProgress() + 2);
                                                           }
                                                           progress_handler.post(progress);
                                                       }

                                                   }
                                               };

    private void changecolor() {
        new Thread() {

            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }

        }.start();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * 
     * @param dpvalue
     * @return
     */
    public int dipTopx(float dpvalue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpvalue * scale + 0.5f);
    }
}
