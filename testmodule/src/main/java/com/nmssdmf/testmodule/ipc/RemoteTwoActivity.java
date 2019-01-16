package com.nmssdmf.testmodule.ipc;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.testmodule.R;

public class RemoteTwoActivity extends AppCompatActivity {
    private final String TAG = RemoteTwoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_two);
        JLog.d(TAG, "sUserId = " + UserManager.sUserId);
    }


}
