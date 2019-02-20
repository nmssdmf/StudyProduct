package com.nmssdmf.testmodule.ThreadPoolExecutor;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.testmodule.R;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class ThreadPoolExecutorActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    private final String TAG = ThreadPoolExecutorActivity.class.getSimpleName();
    private ThreadPoolExecutor threadPoolExecutor;

    private final String DOWN_LOAD_APK = "http://jushiyun-line.oss-cn-hangzhou.aliyuncs.com/jushiTrading.apk";
//    private final String DOWN_LOAD_LOGO = "https://www.baidu.com/img/bdlogo.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool_executor);

        Button button = findViewById(R.id.button);

        //可重用固定线程,只有核心线程
        final ExecutorService executorService = Executors.newFixedThreadPool(5);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
                if (!EasyPermissions.hasPermissions(ThreadPoolExecutorActivity.this, perms)) {
                    EasyPermissions.requestPermissions(new PermissionRequest.Builder(ThreadPoolExecutorActivity.this, 1, perms)
                            .setRationale("请求打开文件读写权限")
                            .setPositiveButtonText("ok")
                            .setNegativeButtonText("cancel")
                            .build());
                }

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        HttpClient client = new HttpClient();
                        client.doHeader(DOWN_LOAD_APK, new HttpResponseCall() {
                            @Override
                            public void onFailure() {

                            }

                            @Override
                            public void onResponseError() {

                            }

                            @Override
                            public void onResponseSuccess() {

                            }
                        });
                    }
                };
                executorService.execute(runnable);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        JLog.d(TAG, "onPermissionsGranted");
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        JLog.d(TAG, "onPermissionsDenied");
    }
}
