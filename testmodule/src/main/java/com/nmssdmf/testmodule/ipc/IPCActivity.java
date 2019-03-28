package com.nmssdmf.testmodule.ipc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nmssdmf.testmodule.R;
import com.nmssdmf.testmodule.ipc.aidl.AidlActivity;
import com.nmssdmf.testmodule.ipc.messenager.MessengerActivity;

public class IPCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc);
        findViewById(R.id.btnAidl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IPCActivity.this, AidlActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnMessenger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IPCActivity.this, MessengerActivity.class);
                startActivity(intent);
            }
        });
    }
}
