package com.nmssdmf.testmodule.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.testmodule.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxJavaActivity2 extends AppCompatActivity {
    private final String TAG = RxJavaActivity2.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java2);

//        Observable.interval(2, 1, TimeUnit.SECONDS)
//                .doOnNext(new Consumer<Long>() {//doOnNext（）在执行Next事件前调用
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        JLog.d(TAG, "第 " + aLong + " 次轮询" );
//                        // a. 创建Retrofit对象
//                        Retrofit retrofit = new Retrofit.Builder()
//                                .baseUrl("http://fy.iciba.com/")
//                                .addConverterFactory(GsonConverterFactory.create())//设置gson
//                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                                .build();
//
//                        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
//                        Observable<Translation> observable = request.getCall();
//                        observable.subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(new Observer<Translation>() {
//                                    @Override
//                                    public void onSubscribe(Disposable d) {
//
//                                    }
//
//                                    @Override
//                                    public void onNext(Translation value) {
//                                        //接受到服务器的数据
//                                        value.show();
//                                    }
//
//                                    @Override
//                                    public void onError(Throwable e) {
//                                        JLog.d(TAG, "请求失败");
//                                    }
//
//                                    @Override
//                                    public void onComplete() {
//
//                                    }
//                                });
//                    }
//                }).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Long value) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                JLog.d(TAG, "对Error事件作出响应");
//            }
//
//            @Override
//            public void onComplete() {
//                JLog.d(TAG, "对Complete事件作出响应");
//            }
//        });


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())//设置gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofit.create(GetRequest_Interface.class)
                .getRegister()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Translation1>() {
                    @Override
                    public void accept(Translation1 translation1) throws Exception {
                        JLog.d(TAG, "第一次网络调用成功");
                        translation1.show();
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<Translation1, ObservableSource<Translation2>>() {
                    @Override
                    public ObservableSource<Translation2> apply(Translation1 translation1) throws Exception {
                        return retrofit.create(GetRequest_Interface.class).getLogin();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Translation2>() {
                    @Override
                    public void accept(Translation2 translation2) throws Exception {
                        JLog.d(TAG, "第二次网络调用成功");
                        translation2.show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        JLog.d(TAG, "error");
                        throwable.printStackTrace();
                    }
                });
    }
}
