package com.hodanet.sanre.common.base;


import com.hodanet.sanre.common.constant.CommonConstant;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public abstract class BaseHandler extends Handler {
	Context context;

	public BaseHandler(Context context) {
		this.context = context;
	}

	@Override
	public void handleMessage(Message msg) {
		handleMyMessage(msg);
		switch (msg.what) {
		case CommonConstant.RESULT_NETWORK_FAIL:
			Toast.makeText(context, "网络连接不可用！", Toast.LENGTH_SHORT).show();
		}
	}

	public abstract void handleMyMessage(Message msg);
}
