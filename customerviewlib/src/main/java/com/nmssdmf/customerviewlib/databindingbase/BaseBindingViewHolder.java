package com.nmssdmf.customerviewlib.databindingbase;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.nmssdmf.customerviewlib.BaseViewHolder;


/**
 * Created by tysheng
 * Date: 2017/5/11 14:42.
 * Email: tyshengsx@gmail.com
 */

public class BaseBindingViewHolder<Binding extends ViewDataBinding> extends BaseViewHolder {
    private Binding mBinding;

    public BaseBindingViewHolder(View view) {
        super(view);
    }

    public Binding getBinding() {
        return mBinding;
    }

    public void setBinding(Binding binding) {
        mBinding = binding;
    }
}
