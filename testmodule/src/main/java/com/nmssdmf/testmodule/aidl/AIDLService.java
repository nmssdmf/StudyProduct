package com.nmssdmf.testmodule.aidl;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.nmssdmf.commonlib.util.JLog;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by ${nmssdmf} on 2019/1/15 0015.
 */

public class AIDLService extends Service {
    private final String TAG = this.getClass().getSimpleName();

    private CopyOnWriteArrayList<Book> books = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> listeners = new RemoteCallbackList<>();

    private IBinder iBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            books.add(book);
            callback(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            if (listener != null) {
                JLog.d(TAG, "注册成功");
                listeners.register(listener);
            } else {
                JLog.d(TAG, "注册失败");
            }
        }

        @Override
        public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            if (listener != null) {
                listeners.unregister(listener);
                JLog.d(TAG, "移除成功");
            } else {
                JLog.d(TAG, "不存在");
            }
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }
    };

    public void callback(Book book){
        int N = listeners.beginBroadcast();
        for (int i = 0; i < N; i++) {
            try {
                listeners.getBroadcastItem(i).onNewBookArrived(book);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        listeners.finishBroadcast();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        books.add(new Book(1, "android"));
        books.add(new Book(2, "ios"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        JLog.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        JLog.d(TAG, "bindService");
        return super.bindService(service, conn, flags);
    }

    @Override
    public void onRebind(Intent intent) {
        JLog.d(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        JLog.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        JLog.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
