package com.nmssdmf.testmodule.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class TestBindService extends Service {
    private final String TAG = TestBindService.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "TestBindService onCreate");
        Log.d(TAG, "Thread id = " + Thread.currentThread().getId());
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "TestBindService onBind");
        Log.d(TAG, "Thread id = " + Thread.currentThread().getId());
        return null;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Log.d(TAG, "TestBindService unbindService");
        Log.d(TAG, "Thread id = " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "TestBindService onDestroy");
        Log.d(TAG, "Thread id = " + Thread.currentThread().getId());
    }
}
