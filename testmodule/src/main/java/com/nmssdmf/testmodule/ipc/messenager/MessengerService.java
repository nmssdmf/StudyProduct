package com.nmssdmf.testmodule.ipc.messenager;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * 创建一个Service来处理客户端的连接请求，同时创建一个Handler并通过它来创建一个Messenger对象，
 * 然后再Service的onBind中返回这个Messenger对象的底层Binder。通过msg的replyTo来返回消息给客户端。
 * Messenger只能一个个处理消息，并不适合并发请求.
 */
public class MessengerService extends Service {

    private final static String TAG = MessengerService.class.getSimpleName();

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    Log.d(TAG, "receive msg form client:" + msg.getData().getString("msg"));

                    Messenger messenger = msg.replyTo;
                    Message message = Message.obtain(null, 2);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "MessengerSercer");
                    message.setData(bundle);
                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private final Messenger messenger = new Messenger(new MessengerHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
