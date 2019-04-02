package com.nmssdmf.testmodule.designmode.single;

/**
 * 单例模式：单例模式有以下几种实现方式
 *      1、懒汉模式
 *      2、Double Check Lock
 *      3、静态内部类单例模式
 *      4、枚举单例模式
 *      5、使用容器实现单例模式
 *      最合理的实现方式是静态内部类单例模式:
 *          当第一次加载Singleton类时并不会初始化sInstance，只有在第一次调用Singleton的getInstance方法时
 *          才会导致sInstance被初始化，因此，第一次调用getInstance方法会导致虚拟机加载SingletonHolder类，
 *          这种方式不仅能够确保线程安全，也能够保证单例对象的唯一性，同时也延迟了单例的实例化，所以这是
 *          推荐使用的单例模式实现方式。
 */
public class Singleton {
    private Singleton(){}

    public static Singleton getInstance(){
        return SingletonHolder.sInstance;
    }

    /**
     * 静态内部类
     */
    private static class SingletonHolder {
        private static final Singleton sInstance = new Singleton();
    }
}
