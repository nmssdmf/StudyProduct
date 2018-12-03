package com.nmssdmf.testmodule.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.testmodule.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxjavaActivity4 extends AppCompatActivity {
    private final String TAG = RxjavaActivity4.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava4);

//        // concatArray（）：组合多个被观察者一起发送数据（<4个）
//        // 注：串行执行
//        Observable.concat(Observable.just(1, 2), Observable.just(3,4))
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        JLog.d(TAG, "integer = " + integer);
//                    }
//                });
//        // concatArray（）：组合多个被观察者一起发送数据（可＞4个）
//        // 注：串行执行
//        Observable.concatArray(Observable.just(5), Observable.just(6))
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        JLog.d(TAG, "integer = " + integer);
//                    }
//                });
//
//        // concatArray（）：组合多个被观察者一起发送数据:并行（<4个）
//        Observable.merge(Observable.intervalRange(0, 3, 2, 1, TimeUnit.SECONDS),
//                Observable.intervalRange(4, 4, 2, 1, TimeUnit.SECONDS))
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        JLog.d(TAG, "aLong = " + aLong);
//                    }
//                });

//        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                JLog.d(TAG, "被观察者1发送了事件1");
//                emitter.onNext(1);
//                // 为了方便展示效果，所以在发送事件后加入2s的延迟
//                Thread.sleep(1000);
//
//                JLog.d(TAG, "被观察者1发送了事件2");
//                emitter.onNext(2);
//                Thread.sleep(1000);
//
//                JLog.d(TAG, "被观察者1发送了事件3");
//                emitter.onNext(3);
//                Thread.sleep(1000);
//
//                emitter.onComplete();
//
//            }
//        }).subscribeOn(Schedulers.io());
//
//        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                JLog.d(TAG, "被观察者2发送了事件A");
//                emitter.onNext("A");
//                // 为了方便展示效果，所以在发送事件后加入2s的延迟
//                Thread.sleep(1000);
//
//                JLog.d(TAG, "被观察者2发送了事件B");
//                emitter.onNext("B");
//                Thread.sleep(1000);
//
//                JLog.d(TAG, "被观察者2发送了事件C");
//                emitter.onNext("C");
//                Thread.sleep(1000);
//
//                emitter.onComplete();
//            }
//        }).subscribeOn(Schedulers.newThread());
//
//        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
//            @Override
//            public String apply(Integer o, String o2) throws Exception {
//                return o + o2;
//            }
//        }).subscribe(new Observer() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Object value) {
//                JLog.d(TAG, "value = " + value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        Observable.combineLatest(
                Observable.just(1L, 2L, 3L), // 第1个发送数据事件的Observable
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS), // 第2个发送数据事件的Observable：从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                new BiFunction<Long, Long, Long>() {
                    @Override
                    public Long apply(Long o1, Long o2) throws Exception {
                        // o1 = 第1个Observable发送的最新（最后）1个数据
                        // o2 = 第2个Observable发送的每1个数据
                        JLog.e(TAG, "合并的数据是： "+ o1 + " "+ o2);
                        return o1 + o2;
                        // 合并的逻辑 = 相加
                        // 即第1个Observable发送的最后1个数据 与 第2个Observable发送的每1个数据进行相加
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long s) throws Exception {
                JLog.e(TAG, "合并的结果是： "+s);
            }
        });
      
    }
}
