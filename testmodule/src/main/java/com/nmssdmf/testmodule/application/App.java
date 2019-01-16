package com.nmssdmf.testmodule.application;


import android.content.Intent;

import com.nmssdmf.commonlib.application.BaseApplication;
import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.testmodule.service.TestService;

/**
 * Created by ${nmssdmf} on 2018/12/5 0005.
 */

public class App extends BaseApplication{
    private final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this, TestService.class);
        startService(intent);
        JLog.d(TAG, "onCreate 进程创建了");
    }
}
