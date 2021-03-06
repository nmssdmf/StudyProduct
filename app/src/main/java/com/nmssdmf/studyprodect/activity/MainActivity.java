package com.nmssdmf.studyprodect.activity;

import android.os.Bundle;

import com.nmssdmf.commonlib.activity.BaseTitleActivity;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.studyprodect.R;
import com.nmssdmf.studyprodect.databinding.ActivityMainBinding;
import com.nmssdmf.studyprodect.viewmodel.MainVM;


public class MainActivity extends BaseTitleActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
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
        return "新三板";
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        binding.setVm(vm);
        hideNavigation();
    }

    @Override
    public int getContentViewId() {
        return  R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {

    }
}
