package com.nmssdmf.commonlib.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;

import com.nmssdmf.commonlib.R;
import com.nmssdmf.commonlib.callback.BaseTitleRecyclerViewCB;
import com.nmssdmf.commonlib.databinding.ActivityBaseTitleRecyclerviewBinding;
import com.nmssdmf.commonlib.viewmodel.BaseTitleRecyclerViewVM;
import com.nmssdmf.customerviewlib.OnDataChangeListener;
import com.nmssdmf.customerviewlib.databindingbase.BaseDataBindingAdapter;

import java.util.List;

/**
 * Created by ${nmssdmf} on 2018/10/17 0017.
 */

public abstract class BaseTitleRecyclerViewActivity extends BaseTitleActivity implements BaseTitleRecyclerViewCB{
    protected ActivityBaseTitleRecyclerviewBinding binding;
    protected BaseDataBindingAdapter adapter;
    protected BaseTitleRecyclerViewVM vm;

    @Override
    public int getContentViewId() {
        return R.layout.activity_base_title_recyclerview;
    }

    @Override
    public BaseTitleRecyclerViewVM initViewModel() {
        vm = initTitleRecyclerViewViewModel();
        return vm;
    }


    @Override
    public void initContent(Bundle savedInstanceState) {
        adapter = initAdapter(vm.getList());
        binding.crv.setAdapter(adapter);

        binding.crv.setOnDataChangeListener(new OnDataChangeListener() {
            @Override
            public void onRefresh() {
                vm.initData(true);
            }

            @Override
            public void onLoadMore() {
                vm.initData(false);
            }
        });

        vm.initData(false);
    }

    public abstract BaseTitleRecyclerViewVM initTitleRecyclerViewViewModel();
    public abstract BaseDataBindingAdapter initAdapter(List list);

    @Override
    public void refreshAdapter(final boolean isRefresh,final  List dataList) {
        if (isRefresh)
            binding.crv.setRefreshing(false);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataChangedAfterLoadMore(isRefresh, dataList);
            }
        });
    }
}
