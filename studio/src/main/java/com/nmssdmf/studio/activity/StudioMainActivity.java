package com.nmssdmf.studio.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.nmssdmf.commonlib.activity.BaseTitleActivity;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.studio.R;
import com.nmssdmf.studio.databinding.ActivityStudioMainBinding;

public class StudioMainActivity extends BaseTitleActivity {

    private final String TAG = StudioMainActivity.class.getSimpleName();
    private ActivityStudioMainBinding binding;

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
        return "Knowledge";
    }

    @Override
    public void initContent(Bundle savedInstanceState) {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_studio_main;
    }
}
