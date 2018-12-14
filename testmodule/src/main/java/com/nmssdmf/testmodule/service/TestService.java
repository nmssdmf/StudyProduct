package com.nmssdmf.testmodule.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.nmssdmf.commonlib.util.JLog;

/**
 * Created by ${nmssdmf} on 2018/12/14 0014.
 */

public class TestService extends Service {
    private final String TAG = TestService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        JLog.d(TAG, "onBind");
        return null;
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
