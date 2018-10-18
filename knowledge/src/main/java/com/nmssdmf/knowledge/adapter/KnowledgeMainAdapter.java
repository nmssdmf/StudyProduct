package com.nmssdmf.knowledge.adapter;

import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;

import com.nmssdmf.customerviewlib.databindingbase.BaseBindingViewHolder;
import com.nmssdmf.customerviewlib.databindingbase.BaseDataBindingMultiItemQuickAdapter;
import com.nmssdmf.knowledge.R;
import com.nmssdmf.knowledge.bean.KnowledgeBean;

import java.util.List;

/**
 * Created by ${nmssdmf} on 2018/10/18 0018.
 */

public class KnowledgeMainAdapter extends BaseDataBindingMultiItemQuickAdapter<KnowledgeBean> {

    public KnowledgeMainAdapter(@Nullable List data) {
        super(data);
        addItemType(KnowledgeBean.TYPE_GROUP, R.layout.item_knowledge_main_group);
        addItemType(KnowledgeBean.TYPE_ITEM, R.layout.item_knowledge_main_item);
    }


    @Override
    protected void convert2(BaseBindingViewHolder<ViewDataBinding> helper, KnowledgeBean item, int position) {

    }
}
