package com.nmssdmf.testmodule.designmode.single;

import android.util.Log;

import com.nmssdmf.commonlib.util.JLog;

/**
 * 单例模式-double checked locking
 * 优点：采用双锁机制，安全且在多线程情况下能保持高性能
 * 缺点：实现比较复杂
 */
public class Singleton_DCL {
    private final String TAG = Singleton_DCL.class.getSimpleName();
    private static Singleton_DCL instance;

    private Singleton_DCL(){}

    public static Singleton_DCL getInstance(){
        if (instance == null) {
            synchronized (Singleton_DCL.class) {
                if (instance == null) {
                    instance = new Singleton_DCL();
                }
            }
        }
        return instance;
    }

    public void printName(){
        JLog.d(TAG, "单例模式-double checked locking");
    }
}
