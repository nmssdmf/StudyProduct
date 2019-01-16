package com.nmssdmf.testmodule.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.testmodule.R;
import com.nmssdmf.testmodule.aidl.AIDLService;
import com.nmssdmf.testmodule.aidl.Book;
import com.nmssdmf.testmodule.aidl.IBookManager;
import com.nmssdmf.testmodule.aidl.IOnNewBookArrivedListener;

import java.util.List;

public class RemoteOneActivity extends AppCompatActivity {
    private static final String TAG  = RemoteOneActivity.class.getSimpleName();

    private IBookManager mBookManager;
    private Messenger messenger;
    private Messenger replyMessenger = new Messenger(new MessengerHandler());
    private IOnNewBookArrivedListener listener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            JLog.d(TAG, "newBook = " + new Gson().toJson(newBook));
        }
    };

    private ServiceConnection aidlConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mBookManager = IBookManager.Stub.asInterface(service);
            try {
                service.linkToDeath(mDeathRecipient, 0);
                mBookManager.registerListener(listener);

                List<Book> list = mBookManager.getBookList();
                JLog.d(TAG, "list type :" + list.getClass().getCanonicalName());
                JLog.d(TAG, "list data : " + new Gson().toJson(list));
                Book newBook = new Book(3, "Android艺术");
                mBookManager.addBook(newBook);

                list = mBookManager.getBookList();
                JLog.d(TAG, "new list data : " + new Gson().toJson(list));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            JLog.d(TAG, "onServiceDisconnected");
        }
    };

    private ServiceConnection messengerConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            JLog.d(TAG, "onServiceConnected");
            messenger = new Messenger(service);
            Message msg = Message.obtain(null, 1);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "hello");
            msg.setData(bundle);
            msg.replyTo = replyMessenger;
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_one);

        UserManager.sUserId = 2;

        findViewById(R.id.b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mBookManager.unRegisterListener(listener);

                    Book newBook = new Book(4, "Ios艺术");
                    mBookManager.addBook(newBook);
                    List<Book> list = mBookManager.getBookList();
                    JLog.d(TAG, "new list data : " + new Gson().toJson(list));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(RemoteOneActivity.this, RemoteTwoActivity.class);
                startActivity(intent);
            }
        });

//        Intent intent = new Intent(this, MessengerService.class);
        Intent intent = new Intent(this, AIDLService.class);
        startService(intent);
//        bindService(intent, messengerConnection, Context.BIND_AUTO_CREATE);
        bindService(intent, aidlConnection, Context.BIND_AUTO_CREATE);


    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:{
                    JLog.d(TAG, "handleMessage = " + msg.getData().getString("reply"));
                    break;
                }
            }
            super.handleMessage(msg);
        }
    }



    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {//binder的断开连接的时候调用
        @Override
        public void binderDied() {
            if (mBookManager == null) {
                return;
            }
            mBookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
            mBookManager = null;
            //todo 重新绑定Service
            JLog.d(TAG, "binderDied");
        }
    };
}
