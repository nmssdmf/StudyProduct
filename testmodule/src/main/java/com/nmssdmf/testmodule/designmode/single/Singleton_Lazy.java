package com.nmssdmf.testmodule.designmode.single;

import com.nmssdmf.commonlib.util.JLog;

/**
 * 单例模式-懒汉模式
 * 优点：第一次调用才初始化，避免内存浪费
 * 缺点：当多线程平凡调用getInstance时，效率较低
 */
public class Singleton_Lazy {
    private final String TAG = Singleton_Lazy.class.getSimpleName();
    /**
     * 全局变量
     */
    private static Singleton_Lazy instance;

    /**
     * 私有化构造函数
     */
    private Singleton_Lazy(){}

    /**
     * 静态线程安全方法获取实例
     * @return
     */
    public static synchronized  Singleton_Lazy getInstance(){
        if (instance == null)
            instance = new Singleton_Lazy();
        return instance;
    }

    public void printName(){
        JLog.d(TAG, "单例模式-饿汉模式");
    }
}
