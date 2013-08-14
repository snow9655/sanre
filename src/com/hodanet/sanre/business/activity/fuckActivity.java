package com.hodanet.sanre.business.activity;

import com.hodanet.sanre.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

public class fuckActivity extends Activity {

    private LinearLayout bar;
    private Animation    animation;
    private Button       btn_start;
    private Handler      handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);
        bar = (LinearLayout) this.findViewById(R.id.temperature_bar);
        btn_start = (Button) this.findViewById(R.id.btn_start);
        animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 200.0f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        bar.setAnimation(animation);
        btn_start.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                bar.startAnimation(animation);
                // bar.setBackgroundColor(getResources().getColor(R.color.black));
                changecolor();
            }
        });
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    bar.setBackgroundResource(R.drawable.temperature_bar_bule);
                }
            }

        };
    }

    private void changecolor() {
        new Thread() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
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
}
