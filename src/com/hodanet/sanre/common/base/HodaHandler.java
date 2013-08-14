package com.hodanet.sanre.common.base;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.hodanet.sanre.common.constant.CommonConstant;
import com.hodanet.sanre.R;

public abstract class HodaHandler extends Handler {

    Context context;

    public HodaHandler(Context context){
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        handleMyMessage(msg);
        switch (msg.what) {
            case CommonConstant.RESULT_NETWORK_FAIL:
                Toast.makeText(context, R.string.network_unavailable, Toast.LENGTH_SHORT).show();
        }
    }

    public abstract void handleMyMessage(Message msg);

}
