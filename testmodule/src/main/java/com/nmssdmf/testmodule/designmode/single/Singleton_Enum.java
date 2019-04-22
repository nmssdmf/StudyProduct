package com.nmssdmf.testmodule.designmode.single;

import com.nmssdmf.commonlib.util.JLog;

/**
 * 单例模式-枚举
 */
public enum  Singleton_Enum {
    INSTANCE;

    private final String TAG = Singleton_Enum.class.getSimpleName();

    public void printName(){
        JLog.d(TAG, "单例模式-枚举");
    }
}
