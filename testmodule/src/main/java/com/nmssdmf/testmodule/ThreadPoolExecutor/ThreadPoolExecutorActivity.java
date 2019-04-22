package com.nmssdmf.testmodule.ThreadPoolExecutor;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.commonlib.util.PreferenceUtil;
import com.nmssdmf.testmodule.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Response;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;
import retrofit2.Retrofit;

public class ThreadPoolExecutorActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private final String TAG = ThreadPoolExecutorActivity.class.getSimpleName();
    private ExecutorService executorService;

    private final String DOWN_LOAD_FILE = "http://jushiyun-line.oss-cn-hangzhou.aliyuncs.com/jushiTrading.apk";
//    private final String DOWN_LOAD_FILE = "https://www.baidu.com/img/bdlogo.gif";

    private final int M = 1024 * 1024 * 5;
//    private final int M = 400;

    HttpClient client = new HttpClient();
    String FILE_NAME = Environment.getExternalStorageDirectory().getPath() + File.separator + "nmssdmf.apk";

    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool_executor);

        Button button = findViewById(R.id.button);

        //可重用固定线程,只有核心线程
        executorService = Executors.newFixedThreadPool(5);//固定核心线程和最大线程数，并且不会被回收,适合量少，需要响应快的
//        executorService = Executors.newSingleThreadExecutor();//只有一个核心线程，并且不会被回收,只有单个任务排队的
//        executorService = Executors.newCachedThreadPool();//只有非核心线程，并且数量为最大值，有新任务就新建线程，线程空闲60s就回收,适合量大，耗时少的
//        executorService = Executors.newScheduledThreadPool(5);//指定核心线程数，非核心线程数为最大值，非核心线程闲置会被回收,一般执行定时任务和重复性任务


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
                PreferenceUtil.setLongValue(FILE_NAME, 0l);
                file = new File(FILE_NAME);
                try {
                    if (file.exists()) {
                        file.delete();
                        file.createNewFile();
                        JLog.d(TAG, "文件删除");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        client.doHeader(DOWN_LOAD_FILE, new HttpResponseCall() {
                            @Override
                            public void onFailure() {

                            }

                            @Override
                            public void onResponseError() {

                            }

                            @Override
                            public void onResponseSuccess(Response response) {
                                long length = Long.parseLong(response.header("content-length"));
                                JLog.d(TAG, "response:" + length);
                                long count = length / M + 1;//一次下载大小
                                JLog.d(TAG, "count:" + count);
                                for (int i = 0; i < count; i++) {
                                    long start, end;
                                    if (i == count - 1) {
                                        end = length - 1;
                                    } else {
                                        end = (i + 1) * M - 1;
                                    }
                                    start = i * M;
                                    getFile(start, end);
                                }
                            }
                        });
                    }
                };
                executorService.execute(runnable);
            }
        });

    }

    public void getFile(final long start, final long end) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                client.doGetFile(DOWN_LOAD_FILE, new HttpResponseCall() {
                    @Override
                    public void onFailure() {

                    }

                    @Override
                    public void onResponseError() {

                    }

                    @Override
                    public void onResponseSuccess(Response response) {
                        JLog.d(TAG, String.format("fileCount %d-%d: response.code() = %d", start, end, response.code()));
                        JLog.d(TAG, "Thread.currentThread().getId()111 = " + Thread.currentThread().getId());
                        InputStream inputStream = response.body().byteStream();
                        FileUtil.appendFileWithInstram(FILE_NAME, inputStream, start);
                        response.notify();
                    }
                }, start, end);
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
