package com.nmssdmf.testmodule.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TestStartService extends Service {
    private final String TAG = TestStartService.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "TestStartService onCreate");
        Log.d(TAG, "Thread id = " + Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "TestStartService onStartCommand");
        Log.d(TAG, "Thread id = " + Thread.currentThread().getId());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "TestStartService onDestroy");
        Log.d(TAG, "Thread id = " + Thread.currentThread().getId());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
