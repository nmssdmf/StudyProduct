package com.nmssdmf.testmodule.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.customerviewlib.BaseQuickAdapter;
import com.nmssdmf.customerviewlib.CustomerRecyclerView;
import com.nmssdmf.testmodule.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {
    private final String TAG = RxJavaActivity.class.getSimpleName();
    private CustomerRecyclerView crv;
    private RxJavaAdapter adapter;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        crv = findViewById(R.id.crv);
        initList();
        adapter = new RxJavaAdapter(list);
        crv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                itemClick(list.get(position));
            }
        });
    }

    private void initList() {
        list.add("rxJava基本使用");
        list.add("基于事件流的链式调用");
        list.add("Disposable切断连接");
        list.add("rxjava创建操作符");
        list.add("组合/合并操作符");
        list.add("过滤操作符");
        list.add("条件操作符");
        list.add("变换操作符");
        list.add("功能性操作符");
        list.add("背压");
    }

    private void itemClick(String data) {
        switch (data) {
            case "rxJava基本使用": {
                rxJava_1();
                break;
            }
            case "基于事件流的链式调用": {
                rxJava_2();
                break;
            }
            case "Disposable切断连接": {
                rxJava_3();
                break;
            }
            case "rxjava创建操作符": {
                rxJava_4();
                break;
            }
            case "组合/合并操作符": {
                rxJava_5();
                break;
            }
            case "过滤操作符": {
                rxJava_6();
                break;
            }
            case "条件操作符": {
                rxJava_7();
                break;
            }
            case "变换操作符": {
                rxJava_8();
                break;
            }
            case "功能性操作符": {
                rxjava_9();
                break;
            }
            case "背压": {
                rxjava_10();
                break;
            }
        }
    }

    private void rxJava_1() {
        //创建被观察者Observable对象
        //方法1：create
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            //create是Rxjava最基本的创造时间序列的方法
            //当Obsevable被订阅时，OnSubscribe的call（）方法会自动被调用，即事件序列就会依照设定依次被触发
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //通过 ObservableEmitter类对象产生事件并通知观察者
                emitter.onNext("按门铃");
                emitter.onComplete();

            }
        });

        //创建Observer观察者对象
        //方法1：采用Observer接口
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "对Next事件作出响应" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        };

        //订阅
        observable.subscribe(observer);
    }

    private void rxJava_2() {
        Observable.just("A", "B", "C")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "对Next事件" + s + "作出响应");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });

        Observable.just("A", "B", "C")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "对Next事件" + s + "作出响应");
                    }
                });
    }

    private void rxJava_3() {
        //可采用 Disposable.dispose() 切断观察者 与 被观察者 之间的连接
        Observable.just("A", "B", "C")
                .subscribe(new Observer<String>() {
                    //定义disposable
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "对Next事件" + s + "作出响应");
                        if (s.equals("B")) {
                            disposable.dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    private void rxJava_4() {
        //1.通过create（）创建被观察者对象
        Observable.create(new ObservableOnSubscribe<String>() {
            // 2. 在复写的subscribe（）里定义需要发送的事件
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");

                emitter.onComplete();
            }
        });
        ////log
//        create = 1
//        create = 2
//        create = 3

        //just()快速创建，发送事件
        Observable.just(1,2,3,4);
        //log
//        just = 1
//        just = 2
//        just = 3
//        just = 4

        //fromArray,直接发送传入的数组数据
        ////直接传入list，会把整个list当做一个数据发送
        Integer[] items = {1, 2, 3, 4};
        Observable.fromArray(items);
        //fromArray = 1
//        fromArray = 2
//        fromArray = 3
//        fromArray = 4

        //fromIterable 集合元素遍历
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Observable.fromIterable(list);
        //      fromIterable = 1
//                fromIterable = 2
//                fromIterable = 3
//                fromIterable = 4

        //empty 仅发送Complete事件，直接通知完成
//        Observable.empty();
        //仅发送Error事件，直接通知异常
//        Observable.error()
        //不发送任何事件
//        Observable.never();

        //defer（）才动态创建被观察者对象,不管期间i怎么变动，取订阅时的值
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(i);
            }
        });
        i = 11;
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "defer = " + integer);
            }
        });
        //defer = 11


        //timer(),延迟制定的时间后，发送一个0
        //timer操作符默认运行在一个新线程上
        Observable.timer(2, TimeUnit.SECONDS);
        //timer = 0

        //interval() 每隔指定时间 就发送 事件
        //interval默认在computation调度器上执行
        //延迟3s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
        Observable.interval(3, 1, TimeUnit.SECONDS);
//                  interval = 0
//                  interval = 1
//                  interval = 2
//                  interval = 3
//                  ...

        //range 指定初始值和数量，连续发送事件
        Observable.range(1, 4);
//        range = 1
//        range = 2
//        range = 3
//        range = 4


        //intervalRange 每隔指定时间 就发送 事件，可指定发送的数据的数量
        //延迟3s后发送事件，每隔1秒产生1个数字（从0到10）
        Observable.intervalRange(1, 4, 3, 1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "intervalRange = " + aLong);
                    }
                });
//        intervalRange = 1
//        intervalRange = 2
//        intervalRange = 3
//        intervalRange = 4
        //rangeLong 类似于range（），区别在于该方法支持数据类型 = Long
    }

    Integer i = 10;

    private void rxJava_5() {
        //组合操作符
        // concat（）：组合多个被观察者（≤4个）(串行)一起发送数据,结果1,2,3,4, 当发送error事件时，后面将停止发送其他事件
        Observable.concat(Observable.timer(1, TimeUnit.SECONDS), Observable.just(3l, 4l));
        //concat = 0
//        concat = 3
//        concat = 4
        // concatArray（）：组合多个被观察者一起发送数据（可＞4个）结果1,2,3,4,5
        Observable.concatArray(Observable.just(1), Observable.just(2), Observable.just(3), Observable.just(4), Observable.just(5));
        //concatDelayError（）如果第一个发送了error事件，并不影响其他的被观察者发送事件
        //firstElement()//从串行队列依次按顺序遍历事件，取出数据，如果取到，则发送事件，停止遍历。使用场景，从内存，缓存，网络中获取数据，取到则停止

        //merge 组合多个被观察者（≤4个）按时间并行 发送数据
        Observable.merge(Observable.intervalRange(0, 4, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(11, 4, 2, 2, TimeUnit.SECONDS));
        // merge = 0
//        merge = 1
//        merge = 11
//        merge = 2
//        merge = 3
//        merge = 12
//        merge = 13
//        merge = 14
        // mergeArray（） = 组合4个以上的被观察者一起发送数据
        //mergeDelayError（）与concatDelayError（）同理

        //zip将两个事件合并发送
        Observable.zip(Observable.intervalRange(0, 6, 1, 1, TimeUnit.SECONDS),
                Observable.intervalRange(11, 5, 3, 2, TimeUnit.SECONDS), new BiFunction<Long, Long, Long>() {
            @Override
            public Long apply(Long long1, Long long2) throws Exception {
//                Log.d(TAG, "long1 = " +long1 +"   long2 = " + long2);
                return long1 + long2;
            }
        });
        //时间间隔是2s
//           long1 = 0   long2 = 11
//          zip = 11
//           long1 = 1   long2 = 12
//          zip = 13
//           long1 = 2   long2 = 13
//          zip = 15
//           long1 = 3   long2 = 14
//          zip = 17
//           long1 = 4   long2 = 15
//          zip = 19
        
        //combineLatest（）当两个Observables中的任何一个发送了数据后, 将先发送了数据的Observables 的最新（最后）一个数据 与 另外一个Observable发送的每个数据结合
        Observable.combineLatest(
                Observable.intervalRange(10, 4, 2,2,TimeUnit.SECONDS),
                Observable.intervalRange(0, 4, 1, 1, TimeUnit.SECONDS),
                new BiFunction<Long, Long, Long>() {
                    @Override
                    public Long apply(Long aLong, Long aLong2) throws Exception {
//                        Log.d(TAG, "long1 = " +aLong +"   long2 = " + aLong2);
                        return aLong + aLong2;
                    }
                });
        //combineLatestDelayError（）同concatDelayError（）
//        long1 = 10   long2 = 0
//        aLong = 10
//        long1 = 10   long2 = 1
//        aLong = 11
//        long1 = 10   long2 = 2
//        aLong = 12
//        long1 = 11   long2 = 2
//        aLong = 13
//        long1 = 11   long2 = 3
//        aLong = 14
//        long1 = 12   long2 = 3
//        aLong = 15
//        long1 = 13   long2 = 3
//        aLong = 16



        //collect 将被观察者Observable发送的数据事件收集到一个数据结构里,结果，将1,2,3,4,5，6组成了list
        Observable.just(1, 2, 3, 4, 5, 6)
                .collect(new Callable<ArrayList<Integer>>() {
                    @Override
                    public ArrayList<Integer> call() throws Exception {
                        return new ArrayList<>();
                    }
                }, new BiConsumer<ArrayList<Integer>, Integer>() {
                    @Override
                    public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
                        integers.add(integer);
                    }
                });
        // collect = [1, 2, 3, 4, 5, 6]


        //startWith（） / startWithArray（）发送事件前追加事件
        Observable.just(4, 5, 6)
                .startWith(0)  // 追加单个数据 = startWith()
                .startWithArray(1, 2, 3)// 追加多个数据 = startWithArray()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "startWith/startWithArray = " + integer);
                    }
                });
//        startWith/startWithArray = 1
//        startWith/startWithArray = 2
//        startWith/startWithArray = 3
//        startWith/startWithArray = 0
//        startWith/startWithArray = 4
//        startWith/startWithArray = 5
//        startWith/startWithArray = 6

        //count（）统计发送事件的数量,运行结果为4
        Observable.just(1, 2, 3, 4)
                .count()
        .subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d(TAG, "count = " + aLong);
            }
        });
        //count = 4
    }

    private void rxJava_6() {
        //过滤操作符
        //filter根据条件过滤
        Observable.just(1, 2, 3);
//                .filter(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer > 2;
//                    }
//                })
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        JLog.d(TAG, "integer = " + integer);
//                    }
//                });

        //ofType:根据类型过滤
        Observable.just(1, 2, "3");
//                .ofType(Integer.class)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        JLog.d(TAG, "integer = " + integer);
//                    }
//                });

        //skip:顺数跳过
        //skipLast:倒数跳过
        Observable.just(1, 2, 3);
//                .skip(1)
//                .skipLast(1)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        JLog.d(TAG, "integer = " + integer);
//                    }
//                });

        //distinct:过滤所有重复的信号
        //distinctUntilChanged:只有过滤连续重复的信号
        Observable.just(11, 11, 22, 22, 33, 22);
//                .distinct()
//                .distinctUntilChanged()
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        JLog.d(TAG, "integer = " + integer);
//                    }
//                });

        //take：只接受前面指定的信号
        //takeLast:只接受后面指定的信号
        Observable.just(1, 2, 3, 4, 5, 6);
//                .take(2)
//                .takeLast(2)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        JLog.d(TAG, "integer = " + integer);
//                    }
//                });

        //throttleFirst:每个指定的时间段，只发送该时间段中的第一个数据
        //throttleLast:每个指定的时间段，只发送该时间段中的最后一个数据
        Observable.intervalRange(0, 100, 0, 300, TimeUnit.MILLISECONDS);
//                .throttleLast(1, TimeUnit.SECONDS)
//                .throttleFirst(1, TimeUnit.SECONDS)
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        JLog.d(TAG, "integer = " + aLong);
//                    }
//                });

        //sample与throttleLast类似
        Observable.intervalRange(0, 100, 0, 300, TimeUnit.MILLISECONDS);
//                .sample(1, TimeUnit.SECONDS)
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        JLog.d(TAG, "integer = " + aLong);
//                    }
//                });

        //throttleWithTimeout/debounce:如果两个信号相隔时间少于指定时间，则抛弃前一个信号，取后一个信号
        Observable.intervalRange(0, 10, 0, 300, TimeUnit.MILLISECONDS);
//                .throttleWithTimeout(1, TimeUnit.SECONDS)
//                .debounce(1,TimeUnit.SECONDS)
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        JLog.d(TAG, "integer = " + aLong);
//                    }
//                });

        //firstElement：仅取第一个信号
        //lastElement: 仅取最后一个信号
        //elementAt:取指定序号的信号
        //elementAtOrError:在elementAt的基础上，当出现越界情况（即获取的位置索引 ＞ 发送事件序列长度）时，即抛出异常
        Observable.just(1, 2, 3, 4);
//                .firstElement()
//                .lastElement()
//                .elementAt(2)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        JLog.d(TAG, "integer = " + integer);
//                    }
//                });

    }

    private void rxJava_7() {
        //条件操作符
        //all：将所有信号与指定条件作出判断，所有信号都成立，为true，否则为false
        Observable.just(11, 2, 3, 5);
//                .all(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer < 10;
//                    }
//                })
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        JLog.d(TAG, "aBoolean = " + aBoolean);
//                    }
//                });

        //takeWhile设置指定条件，当元素满足条件则发送，若不满足，则停止发送后面所有信号
        Observable.just(4, 3, 2, 1, 2);
//                .takeWhile(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer > 1;
//                    }
//                })
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        JLog.d(TAG, "integer" + integer);
//                    }
//                });

        //skipWhile,当第一个条件为false时开发发送，后面的信号不再判断条件
        Observable.just(2, 3, 1, 2, 3);
//                .skipWhile(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer > 1;
//                    }
//                })
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        JLog.d(TAG, "integer" + integer);
//                    }
//                });
        //takeUntil 执行到满足条件之后，不再发送信号
        //当参数是Observable时，则当该Observable开始发信号的时候，停止
        Observable.just(2, 3, 1, 2, 3);
//                .takeUntil(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer == 1;
//                    }
//                })
//                .takeUntil(Observable.timer(5, TimeUnit.SECONDS))
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        JLog.d(TAG, "integer" + integer);
//                    }
//                });

        //skipUntil 直到Observable发送信号，才开始发送信号
        Observable.intervalRange(0, 10, 0, 1, TimeUnit.SECONDS);
//                .skipUntil(Observable.timer(5, TimeUnit.SECONDS))
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long integer) throws Exception {
//                        JLog.d(TAG, "integer" + integer);
//                    }
//                });

        //sequenceEqual，判断两个Observable发送的信号是否一样
        Observable.sequenceEqual(Observable.just(1, 2, 3), Observable.just(1, 2));
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        JLog.d(TAG, "aBoolean " + aBoolean);
//                    }
//                });

        //contains判断是否包含指定数据
        Observable.just(1, 2, 3, 4);
//                .contains(3)
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        JLog.d(TAG, "aBoolean " + aBoolean);
//                    }
//                });

        //isEmpty判断发送的信号是否为空
        Observable.empty();
//                .isEmpty()
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        JLog.d(TAG, "aBoolean " + aBoolean);
//                    }
//                });

        //amb只发送第一个先发送信号的Obserable
        List<Observable<Integer>> list = new ArrayList<>();
        list.add(Observable.just(1, 3).delay(100, TimeUnit.MILLISECONDS));
        list.add(Observable.just(2, 4));
        Observable.amb(list);
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        JLog.d(TAG, "integer" + integer);
//                    }
//                });

        //defaultIfEmpty 如果只发送了complete事件，没有发送onnext事件，则发送一个默认值
        Observable.empty().defaultIfEmpty(1).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                JLog.d(TAG, "integer " + o);
            }
        });

    }

    private void rxJava_8() {
        //变换操作符
        //map:数据类型转换
        Observable.just(1, 2, 3)
//                .map(new Function<Integer, String>() {
//                    @Override
//                    public String apply(Integer integer) throws Exception {
//                        return String.valueOf(integer* 2);
//                    }
//                })
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        JLog.d(TAG, "s " + s);
//                    }
//                });

                //flatMap:将整个Observable重新转换发送
//                .flatMap(new Function<Integer, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(Integer integer) throws Exception {
//                        return Observable.just(String.valueOf("s+"+integer));
//                    }
//                })
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String o) throws Exception {
//                        JLog.d(TAG, "s " + o);
//                    }
//                });

                //concatMap 同flatMap
//                .concatMap(new Function<Integer, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(Integer integer) throws Exception {
//                        return Observable.just(String.valueOf("s+"+integer));
//                    }
//                })
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        JLog.d(TAG, "s " + s);
//                    }
//                });
                //buffer定期从 被观察者（Obervable）需要发送的事件中 获取一定数量的事件 & 放到缓存区中，最终发送
                .buffer(3)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        JLog.d(TAG, "integers = " + integers.toString());
                    }
                });
    }

    private void rxjava_9() {
        //功能性操作符
        //delay:指定延迟时间发送
        Observable.just(1, 2, 3);
//                .delay(1, TimeUnit.SECONDS)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        JLog.d(TAG, "integer = " + integer);
//                    }
//                });

        //doOnEach:每次发送信号都会执行,并且在发送信号之前执行,并且在doOnnex之前，包括complete信号
//                .doOnEach(new Consumer<Notification<Integer>>() {
//                    @Override
//                    public void accept(Notification<Integer> integerNotification) throws Exception {
//                        Log.d(TAG, "doOnEach: " + integerNotification.getValue());
//                    }
//                })
//                //doOnNext在onNext信号发送之前调用
//                .doOnNext(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.d(TAG, "doOnNext: " + integer);
//                    }
//                })
//                //doAfterNext:在onnext信号发送之后调用
//                .doAfterNext(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.d(TAG, "doAfterNext: " + integer);
//                    }
//                })
//                //doOnComplete:信号正常发送完成调用
//                .doOnComplete(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        Log.d(TAG, "doOnComplete: ");
//                    }
//                })
//                //doOnError:当发送错误信号时调用
//                .doOnError(new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.d(TAG, "doOnError: " + throwable.getMessage());
//                    }
//                })
//                //doOnSubscribe:当订阅的时候调用
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        Log.d(TAG, "doOnSubscribe: ");
//                    }
//                })
//                //doAfterTerminate:当事件发送完毕时调用,不管正常还是异常,调用时机在finally之后
//                .doAfterTerminate(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        Log.d(TAG, "doAfterTerminate: ");
//                    }
//                })
//                //doFinally:最后执行
//                .doFinally(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        Log.d(TAG, "doFinally: ");
//                    }
//                })
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        JLog.d(TAG, "integer = " + integer);
//                    }
//                });

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onError(new Exception("发送错误"));
            }
        });
        //onErrorReturn当发onError信号的时候调用，处理错误，并返回数据
//                .onErrorReturn(new Function<Throwable, Integer>() {
//                    @Override
//                    public Integer apply(Throwable throwable) throws Exception {
//                        Log.d(TAG, "在onErrorReturn处理了错误: " + throwable.toString());
//                        return 2;
//                    }
//                })
        //onErrorResumeNext:当发送onError信号时候调用，重新发送一个信号
//                .onErrorResumeNext(new Observable<Integer>() {
//                    @Override
//                    protected void subscribeActual(Observer<? super Integer> observer) {
//                        observer.onNext(3);
//                    }
//                })
        //onExceptionResumeNext:只能接受Exception的error，当error之后，重新发送信号
//                .onExceptionResumeNext(new Observable<Integer>() {
//                    @Override
//                    protected void subscribeActual(Observer<? super Integer> observer) {
//                        observer.onNext(3);
//                    }
//                })
        //retry：当发生error时，重新发送信号
        //retry(1)：指定重试次数
        //retry(new Predicate):出现错误后，判断是否需要重新发送数据
//                .retry(new Predicate<Throwable>() {
//                    @Override
//                    public boolean test(Throwable throwable) throws Exception {
//                        return false;
//                    }
//                })
        //retryUntil:出现错误后，判断是否需要重新发送数据
//                .retryUntil(new BooleanSupplier() {
//                    @Override
//                    public boolean getAsBoolean() throws Exception {
//                        return false;
//                    }
//                })
        //// 遇到error事件才会回调
//                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
//                        // 参数Observable<Throwable>中的泛型 = 上游操作符抛出的异常，可通过该条件来判断异常的类型
//                        // 返回Observable<?> = 新的被观察者 Observable（任意类型）
//                        // 此处有两种情况：
//                        // 1. 若 新的被观察者 Observable发送的事件 = Error事件，那么 原始Observable则不重新发送事件：
//                        // 2. 若 新的被观察者 Observable发送的事件 = Next事件 ，那么原始的Observable则重新发送事件：
//
//                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
//                            @Override
//                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
//                                // 1. 若返回的Observable发送的事件 = Error事件，则原始的Observable不重新发送事件
//                                // 该异常错误信息可在观察者中的onError（）中获得
////                                return Observable.error(new Throwable("retryWhen终止啦"));
//                                // 2. 若返回的Observable发送的事件 = Next事件，则原始的Observable重新发送事件（若持续遇到错误，则持续重试）
//                                 return Observable.timer(1000, TimeUnit.MILLISECONDS);//重新发送
//                            }
//                        });
//                    }
//                })
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        JLog.d(TAG, "integer = " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        JLog.d(TAG, "Throwable = " + e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });


        Observable.just(1, 2, 3, 4)
                //repeat:重复发送
//                .repeat(2)
                //repeatWhen,判断条件，重复一次发送
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                        return Observable.timer(1, TimeUnit.SECONDS);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        JLog.d(TAG, "integer = " + integer);
                    }
                });

    }

    private void rxjava_10() {
        //异步背压
//        Flowable.create(new FlowableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
//                JLog.d(TAG, "发送事件1");
//                emitter.onNext(1);
//                JLog.d(TAG, "发送事件2");
//                emitter.onNext(2);
//                JLog.d(TAG, "发送事件3");
//                emitter.onNext(3);
//                JLog.d(TAG, "发送事件4");
//                emitter.onNext(4);
//                JLog.d(TAG, "发送事件5");
//                emitter.onNext(5);
//                JLog.d(TAG, "发送事件完成");
//                emitter.onComplete();
//            }
//        }, BackpressureStrategy.ERROR)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Integer>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        s.request(Long.MAX_VALUE);
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        JLog.d(TAG, "接受事件" + integer);
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                long n = emitter.requested();
                for (int i = 0; i< n;i++) {
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(3);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        JLog.d(TAG, "接受事件" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
