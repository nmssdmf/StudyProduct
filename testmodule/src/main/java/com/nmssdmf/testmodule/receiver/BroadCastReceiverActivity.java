package com.nmssdmf.testmodule.receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nmssdmf.testmodule.R;

/**
 * BroadCastReceiver注册方式：
 *      静态：在Manifest中注册,生命周期不受影响，即使程序被关闭，还是可以收到广播,比较耗电，占内存
 *      动态：在Activity中注册,生命周期受Activity影响，使用比较灵活
 * 使用场景：与系统相关的变化使用广播，其他使用事件总线, 比如网络变化时，先用广播监听，在发送事件总线
 */
public class BroadCastReceiverActivity extends AppCompatActivity {
    private final static String TAG = BroadCastReceiverActivity.class.getSimpleName();

    TestReceiver2 receiver2 = new TestReceiver2();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_receiver);

        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestReceiver2.ACTION);
                Bundle bundle = new Bundle();
                bundle.putString("name", "AAA");
                bundle.putInt("id", 01);
                intent.putExtras(bundle);
                sendBroadcast(intent);

                Intent intent1 = new Intent(TestReceiver1.ACTION);
                Bundle bundle1 = new Bundle();
                bundle1.putString("name", "BBB");
                bundle1.putInt("id", 02);
                intent1.putExtras(bundle1);
                sendBroadcast(intent1);

//                sendOrderedBroadcast();发送有序广播
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(TestReceiver2.ACTION);
        registerReceiver(receiver2, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver2);
    }
}
