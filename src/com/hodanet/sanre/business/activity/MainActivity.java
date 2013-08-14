package com.hodanet.sanre.business.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.hodanet.sanre.R;
import com.hodanet.sanre.common.application.MyApplication;
import com.hodanet.sanre.common.base.HodaActivity;
import com.hodanet.sanre.common.constant.CommonConstant;
import com.hodanet.sanre.common.constant.NetTypeConstant;
import com.hodanet.sanre.common.util.CheckUtil;
import com.hodanet.sanre.common.util.DeviceUuidUtil;
import com.hodanet.sanre.common.util.LogUtil;
import com.hodanet.sanre.common.util.SharedPreferencesUtil;

public class MainActivity extends HodaActivity implements OnClickListener {

    private static final String TAG     = MainActivity.class.getName();
    private ImageView           imageView;

    private Handler             handler = new Handler() {

                                            public void handleMessage(android.os.Message msg) {
                                                LogUtil.i(TAG, "handler start");
                                                switch (msg.what) {
                                                    case NetTypeConstant.NO_NETWORK: {
                                                        Toast.makeText(MainActivity.this, R.string.network_unavailable,
                                                                       Toast.LENGTH_SHORT).show();
                                                        LogUtil.i(TAG, "handler end");
                                                    }
                                                        break;
                                                }
                                            }
                                        };

    @Override
    public void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        check();
        startGuide();
    }

    /**
     * 页面初始化
     */
    private void initView() {
        imageView = (ImageView) findViewById(R.id.sanre_welcome_id);
        imageView.setOnClickListener(this);
    }

    /**
     * 网络检查
     */
    private void check() {
        // 检查有无网络
        if (CheckUtil.checkNetwork(MyApplication.getInstance())) {
            LogUtil.d(TAG, "m_s Has network.");
            // TODO
        } else {
            LogUtil.d(TAG, "No network.");
            handler.sendEmptyMessage(NetTypeConstant.NO_NETWORK);
            return;
        }
    }

    /**
     * 业务引导
     */
    private void startGuide() {
        LogUtil.i(TAG, "startGuide()");

        new Handler().postDelayed(new Runnable() {

            public void run() {
                if (MainActivity.this.isFinishing()) {
                    return;
                }
                startMainActivity();
            }
        }, 3000);
    }

    // 进入模块子页面
    private void startMainActivity() {
        LogUtil.i(TAG, "startMainActivity()");

        Intent intent = new Intent(MainActivity.this, MainTabActivity.class);
        MainActivity.this.startActivity(intent);
        MainActivity.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sanre_welcome_id:
                startMainActivity();
                break;
        }
    }

}
