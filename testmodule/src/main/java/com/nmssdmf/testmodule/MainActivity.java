package com.nmssdmf.testmodule;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.nmssdmf.commonlib.activity.BaseTitleActivity;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.testmodule.databinding.ActivityMainBinding;

public class MainActivity extends BaseTitleActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public BaseVM initViewModel() {
        return null;
    }

    @Override
    public String setTitle() {
        return "测试主页";
    }

    @Override
    public void initContent(Bundle savedInstanceState) {

    }

    @Override
    public ViewDataBinding getContentRootView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, null, false);
        return binding;
    }
}
