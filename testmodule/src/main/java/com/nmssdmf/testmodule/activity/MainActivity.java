package com.nmssdmf.testmodule.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;

import com.nmssdmf.commonlib.activity.BaseTitleActivity;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.customerviewlib.OnDataChangeListener;
import com.nmssdmf.testmodule.R;
import com.nmssdmf.testmodule.adapter.MainAdapter;
import com.nmssdmf.testmodule.bean.MainBean;
import com.nmssdmf.testmodule.callback.MainCB;
import com.nmssdmf.testmodule.databinding.ActivityMainBinding;
import com.nmssdmf.testmodule.viewmodel.MainVM;

import java.util.List;

public class MainActivity extends BaseTitleActivity implements MainCB{

    private final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;
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
        hideNavigation();
        adapter = new MainAdapter(vm.getList());
        binding.crv.setAdapter(adapter);
        binding.crv.setOnDataChangeListener(new OnDataChangeListener() {
            @Override
            public void onRefresh() {
                vm.initList(true);
            }

            @Override
            public void onLoadMore() {
                vm.initList(false);
            }
        });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                vm.initList(false);
            }
        });
    }

    @Override
    public ViewDataBinding getContentRootView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, null, false);
        return binding;
    }

    @Override
    public void refreshAdapter(final boolean isRefresh,final List<MainBean> list) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataChangedAfterLoadMore(isRefresh, list);
            }
        });
    }
}
