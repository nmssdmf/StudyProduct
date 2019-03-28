package com.nmssdmf.testmodule.ipc.messenager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nmssdmf.testmodule.R;

/**
 * 用bindService的方式启动一个Service，在Service连接成功的回调中，创建一个发送消息的Messenger，
 * 再新建一个Handler和Messenger作为接受服务端消息，通过msg.replyTo发送给服务端.消息通过Bunndle传输
 */
public class MessengerActivity extends AppCompatActivity {

    private static final String TAG = MessengerActivity.class.getSimpleName();

    private Messenger messenger;//发送消息的
    private Messenger getMessenger;//接受消息的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        getMessenger = new Messenger(new MessengerHandler());

        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messenger != null) {
                    Message message = Message.obtain(null, 1);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "MessengerClient");
                    message.setData(bundle);
                    message.replyTo = getMessenger;
                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:{
                    Log.d(TAG, "receive msg from server:" + msg.getData().getString("msg"));
                    break;
                }
                default:
                    super.handleMessage(msg);
            }

        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }
}
