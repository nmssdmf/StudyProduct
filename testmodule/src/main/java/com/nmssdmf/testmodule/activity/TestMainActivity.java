package com.nmssdmf.testmodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nmssdmf.commonlib.activity.BaseTitleActivity;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.customerviewlib.BaseQuickAdapter;
import com.nmssdmf.testmodule.R;
import com.nmssdmf.testmodule.ThreadPoolExecutor.ThreadPoolExecutorActivity;
import com.nmssdmf.testmodule.annotation.MainActivity;
import com.nmssdmf.testmodule.asynctask.AsyncTaskActivity;
import com.nmssdmf.testmodule.contentprovider.ContentProviderActivity;
import com.nmssdmf.testmodule.contentprovider.ContentproviderProcessActivity;
import com.nmssdmf.testmodule.customerview.CustomerViewActivity;
import com.nmssdmf.testmodule.databinding.ActivityTestMainBinding;
import com.nmssdmf.testmodule.receiver.BroadCastReceiverActivity;
import com.nmssdmf.testmodule.rxjava.RxJavaActivity;
import com.nmssdmf.testmodule.service.ServiceActivity;

public class TestMainActivity extends BaseTitleActivity implements MainCB{

    private final String TAG = TestMainActivity.class.getSimpleName();

    private ActivityTestMainBinding binding;
    private MainAdapter adapter;
    private MainVM vm;
    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public BaseVM initViewModel() {
        vm = new MainVM(this);
        return vm;
    }

    @Override
    public String setTitle() {
        return "测试主页";
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        binding = (ActivityTestMainBinding) baseViewBinding;
        hideNavigation();

        vm.initList();
        adapter = new MainAdapter(vm.getList());
        binding.crv.setAdapter(adapter);

        onListener();



    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_test_main;
    }


    private void onListener(){
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MainBean mainBean = vm.getList().get(position);
                Class c = null;
                switch (mainBean.getName()) {
                    case "多线程文件下载":{
                        c = ThreadPoolExecutorActivity.class;
                        break;
                    }
                    case "rxjava":{
                        c = RxJavaActivity.class;
                        break;
                    }
                    case "注解":{
                        c = MainActivity.class;
                        break;
                    }
                    case "四大组件之Service" :{
                        c = ServiceActivity.class;
                        break;
                    }
                    case "四大组件之BroadCastReceiver":{
                        c = BroadCastReceiverActivity.class;
                        break;
                    }
                    case "AsyncTask":{
                        c = AsyncTaskActivity.class;
                        break;
                    }
                    case "四大组件之ContentProvider":{
                        c = ContentProviderActivity.class;
                        break;
                    }
                    case "四大组件之ContentProvider进程" :{
                        c = ContentproviderProcessActivity.class;
                        break;
                    }
                    case "自定义view":{
                        c = CustomerViewActivity.class;
                        break;
                    }
                }
                Intent intent = new Intent(TestMainActivity.this, c);
                startActivity(intent);
            }
        });
    }


    /**
     * 面试当中的其他问题
     */
    public void elseQuestion(){
        //1. 如何判断activity是否还在运行
        if (this == null || this.isFinishing() || this.isDestroyed());
        //2. 自定义view 数据保存调用onSaveStateInstance,需要设置id和setSaveEnabled(true)
        //3. 基本类型按值传递，对象按照引用的地址（内存地址）传递
        //4. handler机制
        //5. Parcelable与Serializable
        //6. context
        //7. 如何优化ListView的性能  重用ConvertView  使用View Holder模式  快速滑动时不要加载图片 尽可能减少List Item的Layout层次
        //8. AsyncTask
    }

    /**
     * 安卓版本适配问题
     */
    public void adaptiveVersion() {
        //6.0 的动态权限申请
        //7.0 对文件权限进一步升级，提出了新的类FileProvider来获取文件
        //8.0 引入了新的广播接收器限制，因此您应该移除所有为隐式广播 Intent 注册的广播接收器。
        //9.0 Android P 限制了明文流量的网络请求，非加密的流量请求都会被系统禁止掉
    }

}
