package com.nmssdmf.testmodule.rxjava;

import android.support.annotation.Nullable;

import com.nmssdmf.customerviewlib.databindingbase.BaseBindingViewHolder;
import com.nmssdmf.customerviewlib.databindingbase.BaseDataBindingAdapter;
import com.nmssdmf.testmodule.R;
import com.nmssdmf.testmodule.databinding.ItemRxjavaBinding;

import java.util.List;

/**
 * Created by ${nmssdmf} on 2019/2/25 0025.
 */

public class RxJavaAdapter extends BaseDataBindingAdapter<String, ItemRxjavaBinding> {
    public RxJavaAdapter(@Nullable List<String> data) {
        super( R.layout.item_rxjava, data);
    }

    @Override
    protected void convert2(BaseBindingViewHolder<ItemRxjavaBinding> helper, String item, int position) {
        helper.getBinding().setData(item);
    }
}
