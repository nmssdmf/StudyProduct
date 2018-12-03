package com.nmssdmf.testmodule.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.testmodule.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxJavaActivity3 extends AppCompatActivity {
    private final String TAG = RxJavaActivity3.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java3);

        Observable.just(1, 2, 3)
                //使用Map变换操作符中的Function函数对被观察者发送的事件进行统一变换：整型变换成字符串类型
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer value) throws Exception {
                        return "第" + value + "个";
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                JLog.d(TAG, "value = " + s);
            }
        });

        Observable.just(1, 2, 3)
                //// 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                //新合并生成的事件序列顺序是无序的，即 与旧序列发送事件的顺序无关
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            list.add("我是事件 " + integer + "拆分后的子事件" + i);
                        }
                        return Observable.fromIterable(list);
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                JLog.d(TAG, "s = " + s);
            }
        });

        Observable.just(1, 2, 3)
                //// 通过concatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                //新合并生成的事件序列顺序是有序的，即 严格按照旧序列发送事件的顺序
                .concatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            list.add("我是事件 " + integer + "拆分后的子事件" + i);
                        }
                        return Observable.fromIterable(list);
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                JLog.d(TAG, "s = " + s);
            }
        });
    }
}
