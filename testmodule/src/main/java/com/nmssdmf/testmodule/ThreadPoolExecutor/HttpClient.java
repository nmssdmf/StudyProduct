package com.nmssdmf.testmodule.ThreadPoolExecutor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public OkHttpClient createOkHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addNetworkInterceptor(new HeaderInterceptor());
        builder.addInterceptor(new LogInterceptor());
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }


    public void doHeader(final String url, final HttpResponseCall httpResponseCall){
        OkHttpClient client = createOkHttpClient();

        final Request request = new Request.Builder()
                .url(url)
                .head()
                .build();
        try {
            Response response = client.newCall(request).execute();
            httpResponseCall.onResponseSuccess(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                call.cancel();
//                httpResponseCall.onFailure();
//                JLog.d(TAG, "onFailure");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                httpResponseCall.onResponseSuccess(response);
//                call.cancel();
//            }
//        });
    }

    /**
     * 大文件的多线程下载
     * @param url
     * @param httpResponseCall
     */
    public void doGetFile(String url, final HttpResponseCall httpResponseCall, final long start, final long end){
        OkHttpClient client = createOkHttpClient();

        final Request request = new Request.Builder()
//                .addHeader("Content-Range", String.format("bytes %d-%d/%d", start, end, all))
                .addHeader("Range", String.format("bytes=%d-%d", start, end))
                .url(url)
                .build();
        try {
            Response  response = client.newCall(request).execute();
            httpResponseCall.onResponseSuccess(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                call.cancel();
//                httpResponseCall.onFailure();
//                JLog.d(TAG, "onFailure");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                httpResponseCall.onResponseSuccess(response);
//            }
//        });
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
