package com.nmssdmf.commonlib.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.nmssdmf.commonlib.R;
import com.nmssdmf.commonlib.databinding.ActivityBaseTitleRecyclerviewBinding;

/**
 * Created by ${nmssdmf} on 2018/10/17 0017.
 */

public abstract class BaseTitleRecyclerViewActivity extends BaseTitleActivity {
    protected ActivityBaseTitleRecyclerviewBinding binding;

    @Override
    public ViewDataBinding getContentRootView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base_title_recyclerview, null, false);
        return binding;
    }
}
