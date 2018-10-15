package com.nmssdmf.commonlib.application;

import android.app.Application;

import com.nmssdmf.commonlib.util.ToastUtil;

/**
 * Created by ${nmssdmf} on 2018/10/15 0015.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Toast
        ToastUtil.getInstance().init(this);
    }
}
