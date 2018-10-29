package com.nmssdmf.knowledge.adapter;

import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.View;

import com.nmssdmf.customerviewlib.databindingbase.BaseBindingViewHolder;
import com.nmssdmf.customerviewlib.databindingbase.BaseDataBindingMultiItemQuickAdapter;
import com.nmssdmf.knowledge.R;
import com.nmssdmf.knowledge.bean.KnowledgeBean;
import com.nmssdmf.knowledge.databinding.ItemKnowledgeMainGroupBinding;
import com.nmssdmf.knowledge.databinding.ItemKnowledgeMainItemBinding;

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
    protected void convert2(BaseBindingViewHolder<ViewDataBinding> helper, final KnowledgeBean item, final int position) {
        if (item.getItemType() == KnowledgeBean.TYPE_GROUP) {
            ItemKnowledgeMainGroupBinding binding = (ItemKnowledgeMainGroupBinding) helper.getBinding();
            binding.setData(item);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.isUnfold()) {
                        mData.removeAll(item.getItems());
                        notifyItemRangeChanged(position, item.getItems().size());
                        item.setUnfold(false);
                    } else {
                        mData.addAll(position + 1, item.getItems());
                        notifyItemRangeInserted(position + 1, item.getItems().size());
                        item.setUnfold(true);
                    }
                }
            });
        } else if (item.getItemType() == KnowledgeBean.TYPE_ITEM) {
            ItemKnowledgeMainItemBinding binding = (ItemKnowledgeMainItemBinding) helper.getBinding();
            binding.setData(item);
        }
    }
}
