package com.nmssdmf.testmodule.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.testmodule.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * rxjava学习
 * 1、创建被观察者Observable
 * 2、创建观察者Observer/Subscribe(Subscribe =  RxJava 内置的一个实现了 Observer 的抽象类，对 Observer 接口进行了扩展)
 * 3、通过订阅（Subscribe）连接观察者和被观察者
 * 大神教程：@https://blog.csdn.net/carson_ho/article/list/4
 */
public class RxJavaActivity extends AppCompatActivity {
    private final String TAG = RxJavaActivity.class.getSimpleName();
    Integer i = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

//Mode:1 写法
        //分布写法

//        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onComplete();
//            }
//        });
//
//        Observer<Integer> observer = new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                JLog.d(TAG, "开始采用subscribe连接");
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                JLog.d(TAG, "对Next事件"+ value +"作出响应"  );
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
//        };
//
//        observable.subscribe(observer);

        //连续写法  create为基本创建

//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onComplete();
//            }
//        }).subscribe(new Observer<Integer>() {
//            Disposable disposable;
//            @Override
//            public void onSubscribe(Disposable d) {
//                JLog.d(TAG, "开始采用subscribe连接");
//                disposable = d;
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                JLog.d(TAG, "对Next事件"+ value +"作出响应"  );
//                if (value == 2) {
//                    // 设置在接收到第二个事件后切断观察者和被观察者的连接
//                    disposable.dispose();
//                    JLog.d(TAG, "已经切断了连接：" + disposable.isDisposed());
//                }
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

//Mode2:创建操作符
        //快速创建just  适用于10个以下的事件 just:相当于执行了onNext(1)、onNext(2)、onNext(3)、onComplete()
//        Observable.just(1, 2, 3).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                JLog.d(TAG, "开始采用subscribe连接");
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                JLog.d(TAG, "对Next事件"+ value +"作出响应"  );
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

        //快速创建 fromArray 发送10个以上事件,数组遍历
//        Integer[] items = {1, 2, 3};
//        Observable.fromArray(items).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                JLog.d(TAG, "开始采用subscribe连接");
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                JLog.d(TAG, "对Next事件"+ value +"作出响应"  );
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

        //快速创建 fromIterable：遍历list 发送10个以上事件（集合形式）
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        Observable.fromIterable(list).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                JLog.d(TAG, "开始采用subscribe连接");
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                JLog.d(TAG, "对Next事件"+ value +"作出响应"  );
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


        //延迟发送  defer:等observable被subscribe了才发送事件
//        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<Integer>>() {
//            @Override
//            public ObservableSource<Integer> call() throws Exception {
//                return Observable.just(i);
//            }
//        });
//
//        i = 15;
//
//        observable.subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                JLog.d(TAG, "开始采用subscribe连接");
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                JLog.d(TAG, "对Next事件"+ value +"作出响应"  );
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


        //延迟发送 timer  :timer操作符默认运行在一个新线程上, 延迟2秒发送一个0
//        Observable.timer(2, java.util.concurrent.TimeUnit.SECONDS).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                JLog.d(TAG, "开始采用subscribe连接");
//            }
//
//            @Override
//            public void onNext(Long value) {
//                JLog.d(TAG, "对Next事件"+ value +"作出响应"  );
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

        //interval
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        //延迟1s后发送事件，每隔2秒产生1个数字（从0开始递增1，无限个）
//        Observable.interval(1, 2, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                JLog.d(TAG, "开始采用subscribe连接");
//            }
//
//            @Override
//            public void onNext(Long value) {
//                JLog.d(TAG, "对Next事件"+ value +"作出响应"  );
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

        //intervalRange
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 参数3 = 第1次事件延迟发送时间；
        // 参数4 = 间隔时间数字；
        // 参数5 = 时间单位
        //延迟3秒开始发送，从10开始，每个两秒发送一次，一共发送5次
//        Observable.intervalRange(10, 5, 3, 2, TimeUnit.SECONDS)
//                .subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                JLog.d(TAG, "开始采用subscribe连接");
//            }
//
//            @Override
//            public void onNext(Long value) {
//                JLog.d(TAG, "对Next事件"+ value +"作出响应"  );
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

        //range
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 注：若设置为负数，则会抛出异常
        //从10开始发送，每次发送事件递增1，一共发送5个事件,没有延时
        Observable.range(10, 5)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        JLog.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        JLog.d(TAG, "对Next事件"+ value +"作出响应"  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        JLog.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        JLog.d(TAG, "对Complete事件作出响应");
                    }
                });
    }
}
