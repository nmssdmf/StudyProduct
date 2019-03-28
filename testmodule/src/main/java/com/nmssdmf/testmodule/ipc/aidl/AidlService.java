package com.nmssdmf.testmodule.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * AIDL方法时在服务端的Binder线程池中执行的，因此当多个客户端同时连接的时候，会存在多线程访问的情况
 * AIDL支持的数据类型
 *      基本类型
 *      String和CharSequence
 *      List:里面每个元素都支持AIDL
 *      Map：里面每个key和value都支持AIDL
 *      Parcelable:所有实现了Parcelable接口的对象
 *      AIDL：AIDL接口本身，也可以在AIDL文件中使用
 * 服务端实现：
 *      继承Service
 *      new Binder实现AIDL接口
 *      在Service的onBind方法中返回Binder
 */
public class AidlService extends Service {
    private final static String TAG = AidlService.class.getSimpleName();
    private CopyOnWriteArrayList<Book> bookList = new CopyOnWriteArrayList<>();
    //经过Binder获得对象与在客户端传递的对象不是同一个对象，所以此时无法通过这种方式注册与解注册
    //这里用到一个RemoteCallbackList
//    private CopyOnWriteArrayList<IOnNewBookArrivedListener> listeners = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> listeners = new RemoteCallbackList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        bookList.add(new Book(1, "深入理解Java虚拟机"));
        bookList.add(new Book(2, "Android开发艺术探索"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        int check = checkCallingOrSelfPermission("com.nmssdmf.testmodule.AIDL");//判断权限
        if (check == PackageManager.PERMISSION_DENIED) {
            Log.d(TAG, "没有权限");
            return null;
        }
        return binder;
    }

    private Binder binder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return bookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            bookList.add(book);
            onNewBookArrived(book);
        }

        private synchronized void onNewBookArrived(Book book){
            final int N = listeners.beginBroadcast();
            for (int i =0; i< N; i++){
                try {
                    listeners.getBroadcastItem(i).onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            listeners.finishBroadcast();//beginBroadcast之后，必须finishBroadcast
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            listeners.register(listener);
        }

        @Override
        public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            listeners.unregister(listener);
        }
    };
}
