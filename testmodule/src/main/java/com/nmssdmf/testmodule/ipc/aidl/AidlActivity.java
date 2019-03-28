package com.nmssdmf.testmodule.ipc.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.nmssdmf.testmodule.R;

import java.util.List;

/**
 * AIDL客户端
 * BindService，在Service绑定的回调中获取AIDL的接口实例，后面通过AIDL的接口实例对服务端进行操作
 * <p>
 * 当客户端调用服务端的方法，此时被调用的方法运行在服务端的binder线程池中，同时客户端的线程会被挂起，
 * 如果这个时候服务端的方法比较耗时，客户端如果是UI线程，会导致无响应，ANR，同理，当服务端调用客户端也一样。
 */
public class AidlActivity extends AppCompatActivity {
    private final String TAG = AidlActivity.class.getSimpleName();

    private IBookManager iBookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iBookManager.addBook(new Book(3, "Linux网络开发"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<Book> list = iBookManager.getBookList();
                    Log.d(TAG, "book list = " + new Gson().toJson(list));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private IOnNewBookArrivedListener listener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            Log.d(TAG, "newBook = " + new Gson().toJson(newBook));
        }
    };

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service == null)
                return;
            try {
                service.linkToDeath(deathRecipient, 0);//监听Binder断线
                iBookManager = IBookManager.Stub.asInterface(service);
                iBookManager.registerListener(listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (iBookManager == null) {
                return;
            }
            iBookManager.asBinder().unlinkToDeath(deathRecipient, 0);//解除
            iBookManager = null;
            //再重新启动Service
            Intent intent = new Intent(AidlActivity.this, AidlService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    };


    @Override
    protected void onDestroy() {
        try {
            iBookManager.unRegisterListener(listener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        unbindService(connection);
        super.onDestroy();
    }
}
