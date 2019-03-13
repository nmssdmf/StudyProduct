package com.nmssdmf.testmodule.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class TestIntentService extends IntentService {
    private final String TAG = TestIntentService.class.getSimpleName();

    public TestIntentService() {
        super("TestIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "TestIntentService onCreate");
        Log.d(TAG, "Thread id = " + Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG, "TestIntentService onStartCommand");
        Log.d(TAG, "Thread id = " + Thread.currentThread().getId());
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public TestIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent");
        Log.d(TAG, "Thread id = " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "TestIntentService onDestroy");
        Log.d(TAG, "Thread id = " + Thread.currentThread().getId());
        super.onDestroy();
    }
}
