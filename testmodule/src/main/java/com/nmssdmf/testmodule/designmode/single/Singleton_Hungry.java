package com.nmssdmf.testmodule.designmode.single;

import com.nmssdmf.commonlib.util.JLog;

/**
 * 单例模式-饿汉模式
 * 优点：不需要线程同步
 * 缺点：在类加载的时候就初始化，浪费内存资源
 */
public class Singleton_Hungry {
    private final String TAG = Singleton_Hungry.class.getSimpleName();
    /**
     * 类加载时就初始化
     */
    private static Singleton_Hungry instance = new Singleton_Hungry();

    /**
     * 私有构造函数
     */
    private Singleton_Hungry(){}

    /**
     * 静态方法获取实例
     * @return
     */
    public static Singleton_Hungry getInstance(){
        return instance;
    }

    public void printName(){
        JLog.d(TAG, "单例模式-饿汉模式");
    }
}
