package com.nmssdmf.testmodule.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nmssdmf.testmodule.R;

/**
 * Service:启动方式分两种
 *      1.startService: 在调用startService的时候，回调生命周期：onCreate->onStartCommand,在调用stopService的时候，回调生命周期onDestroy
 *          并且运行在主线程
 *      2.bindService: 在调用bindService的时候(需要ServiceConnection参数)，回调生命周期：onCreate->onBind，在调用unbindService的时候，
 *          回调生命周期onDestroy,并且运行在主线程中
 * IntentService:只有startService这一种启动方式
 *      1.在调用startService的时候，回调生命周期，onCreate->onStartCommand->onHandleIntent->onDestroy，并且，onHandleIntent在异步线程中进行，
 *          其余生命周期在主线程中进行，不需要单独调用stopService，线程运行结束，自动回调onDestroy
 *      2.原理：IntentService继承Service，在Service的onCreate方法中，生成一个HandlerThread和ServiceHandler，HandlerThread提供异步操作，
 *          在onStart的时候，ServiceHandler发送消息，在ServiceHandler中处理，回调onHandleIntent方法，然后stopService。
 * 设置Service的优先级:
 *      1.在onStartCommand中调用startForeground方法，将service提升为前台进程级别，然后再onDestory里面调用stopForeground方法
 */
public class ServiceActivity extends AppCompatActivity {
    private final String TAG = ServiceActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Log.d(TAG, "Thread id = " + Thread.currentThread().getId());

        //startService
        final Intent intent = new Intent();
        intent.setClass(ServiceActivity.this, TestStartService.class);

        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        findViewById(R.id.btnStopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });

        //bindingService
        final ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected");
            }
        };

        final Intent intent1 = new Intent();
        intent1.setClass(ServiceActivity.this, TestBindService.class);

        findViewById(R.id.btnBind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent1, connection, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.btnUnBindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });

        //intentService
        final Intent intent2 = new Intent();
        intent2.setClass(this, TestIntentService.class);

        findViewById(R.id.btnIntentService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent2);
            }
        });
    }
}
