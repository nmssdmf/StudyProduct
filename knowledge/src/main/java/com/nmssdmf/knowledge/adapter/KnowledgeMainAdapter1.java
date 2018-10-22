package com.nmssdmf.knowledge.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.nmssdmf.knowledge.R;
import com.nmssdmf.knowledge.bean.KnowledgeBean;
import com.nmssdmf.knowledge.databinding.ItemKnowledgeMainGroupBinding;
import com.nmssdmf.knowledge.databinding.ItemKnowledgeMainItemBinding;

import java.util.List;

/**
 * Created by ${nmssdmf} on 2018/10/22 0022.
 */

public class KnowledgeMainAdapter1 extends BaseExpandableListAdapter {
    private List<KnowledgeBean> list;
    private Context context;
    public KnowledgeMainAdapter1(List<KnowledgeBean> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (list.get(groupPosition).getItems() == null)
            return 0;
        return list.get(groupPosition).getItems().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getItems().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ItemKnowledgeMainGroupBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_knowledge_main_group, null, false);
        binding.setData(list.get(groupPosition));
        return binding.getRoot();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemKnowledgeMainItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_knowledge_main_item, null, false);
        binding.setData(list.get(groupPosition).getItems().get(childPosition));
        return binding.getRoot();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
