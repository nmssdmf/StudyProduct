package com.nmssdmf.testmodule.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.nmssdmf.commonlib.util.JLog;

/**
 * Created by ${nmssdmf} on 2019/1/15 0015.
 */

public class MessengerService extends Service {
    private static final String TAG = MessengerService.class.getSimpleName();

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:{
                    JLog.d(TAG, "handleMessage = " + msg.getData().getString("msg"));
                    Message replyMsg = Message.obtain(null, 1);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "收到");
                    replyMsg.setData(bundle);
                    try {
                        msg.replyTo.send(replyMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            super.handleMessage(msg);
        }
    }

    private final Messenger messenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        JLog.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        JLog.d(TAG, "bindService");
        return super.bindService(service, conn, flags);
    }

    @Override
    public void onRebind(Intent intent) {
        JLog.d(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        JLog.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        JLog.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
