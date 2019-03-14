package com.nmssdmf.testmodule.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ${nmssdmf} on 2019/3/14 0014.
 */

public class TestReceiver1 extends BroadcastReceiver {
    private final static String TAG = TestReceiver1.class.getSimpleName();

    public final static String ACTION = TestReceiver1.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getExtras() != null) {
                String name = intent.getExtras().getString("name");
                int id = intent.getExtras().getInt("id");
                Log.d(TAG, "id = " + id + " ; name = " + name);
            }
    }
}
