package com.nmssdmf.testmodule.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ${nmssdmf} on 2019/3/14 0014.
 * BroadCastReceiver注册方式：
 *      静态：在Manifest中注册
 *      动态：在Activity中注册
 */

public class TestReceiver2 extends BroadcastReceiver {
    private final static String TAG = TestReceiver2.class.getSimpleName();

    public static final String ACTION = TestReceiver2.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getExtras() != null) {
            String name = intent.getExtras().getString("name");
            int id = intent.getExtras().getInt("id");
            Log.d(TAG, "id = " + id + " ; name = " + name);
        }
    }
}
