package com.nmssdmf.testmodule.ThreadPoolExecutor;

import android.os.Environment;

import com.nmssdmf.commonlib.util.JLog;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ${nmssdmf} on 2019/2/19 0019.
 */

public class HttpClient {
    private final String TAG = HttpClient.class.getSimpleName();
    private final int M = 1024*1024*5;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public OkHttpClient createOkHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addNetworkInterceptor(new HeaderInterceptor());
        builder.addInterceptor(new LogInterceptor());
        return builder.build();
    }


    public void doHeader(final String url, final HttpResponseCall httpResponseCall){
        OkHttpClient client = createOkHttpClient();

        final Request request = new Request.Builder()
                .url(url)
                .head()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                httpResponseCall.onFailure();
                JLog.d(TAG, "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                long length = Long.parseLong(response.header("content-length")) - 1;
                JLog.d(TAG, "response:" + length);
                long count = length / M + 1;//一次下载大小
                JLog.d(TAG, "count:" + count);
                for (int i = 0; i < count; i++) {
                    long start, end;
                    if (i == count - 1) {
                        end = length;
                    } else {
                        end = (i+1)*M - 1;
                    }
                    start = i * M;
                    doGetFile(url, new HttpResponseCall() {
                        @Override
                        public void onFailure() {

                        }

                        @Override
                        public void onResponseError() {

                        }

                        @Override
                        public void onResponseSuccess() {

                        }
                    }, start, end, length);
                }
            }
        });
    }

    /**
     * 大文件的多线程下载
     * @param url
     * @param httpResponseCall
     */
    public void doGetFile(String url, final HttpResponseCall httpResponseCall, final long start, final long end, long all){
        OkHttpClient client = createOkHttpClient();

        final Request request = new Request.Builder()
//                .addHeader("Content-Range", String.format("bytes %d-%d/%d", start, end, all))
                .addHeader("Range", String.format("bytes=%d-%d", start, end))
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                httpResponseCall.onFailure();
                JLog.d(TAG, "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JLog.d(TAG, String.format("fileCount %d-%d: response.code() = %d", start, end ,response.code()));
                String fileName = Environment.getExternalStorageDirectory().getPath()+ File.separator+"nmssdmf.apk";
                JLog.d(TAG, "fileName :" + fileName);
//                byte[] bytes = response.body().bytes();
//                FileUtil.appendFile(fileName, bytes, start, M);
                synchronized (response) {
                    JLog.d(TAG, "Thread.currentThread().getId()111 = " + Thread.currentThread().getId());
                    InputStream inputStream = response.body().byteStream();
                    JLog.d(TAG, "Thread.currentThread().getId()222 = " + Thread.currentThread().getId());
//                response.close();
                    FileUtil.appendFileWithInstram(fileName, inputStream, start, M);
                    response.close();
                    response.notify();
                }
            }
        });
    }

    public void doPost(String url, String json, final HttpResponseCall httpResponseCall){
        OkHttpClient client = createOkHttpClient();

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                httpResponseCall.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
