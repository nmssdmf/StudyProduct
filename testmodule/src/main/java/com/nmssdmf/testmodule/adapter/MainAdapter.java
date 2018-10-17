package com.nmssdmf.testmodule.adapter;

import android.support.annotation.Nullable;

import com.nmssdmf.customerviewlib.base.databindingbase.BaseBindingViewHolder;
import com.nmssdmf.customerviewlib.base.databindingbase.BaseDataBindingAdapter;
import com.nmssdmf.testmodule.R;
import com.nmssdmf.testmodule.bean.MainBean;
import com.nmssdmf.testmodule.databinding.ItemMainBinding;

import java.util.List;

/**
 * Created by ${nmssdmf} on 2018/10/16 0016.
 */

public class MainAdapter extends BaseDataBindingAdapter<MainBean, ItemMainBinding> {

    public MainAdapter( @Nullable List<MainBean> data) {
        super(R.layout.item_main, data);
    }

    @Override
    protected void convert2(BaseBindingViewHolder<ItemMainBinding> helper, MainBean item, int position) {
        helper.getBinding().setData(item);
    }
}
