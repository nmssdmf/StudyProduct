package com.nmssdmf.testmodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nmssdmf.commonlib.activity.BaseTitleActivity;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.customerviewlib.BaseQuickAdapter;
import com.nmssdmf.testmodule.R;
import com.nmssdmf.testmodule.ThreadPoolExecutor.ThreadPoolExecutorActivity;
import com.nmssdmf.testmodule.adapter.MainAdapter;
import com.nmssdmf.testmodule.bean.MainBean;
import com.nmssdmf.testmodule.callback.MainCB;
import com.nmssdmf.testmodule.databinding.ActivityTestMainBinding;
import com.nmssdmf.testmodule.rxjava.RxJavaActivity;
import com.nmssdmf.testmodule.sort.SortActivity;
import com.nmssdmf.testmodule.viewmodel.MainVM;

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
                    case "sort":{
                        c = SortActivity.class;
                        break;
                    }
                }
                Intent intent = new Intent(TestMainActivity.this, c);
                startActivity(intent);
            }
        });
    }

}
