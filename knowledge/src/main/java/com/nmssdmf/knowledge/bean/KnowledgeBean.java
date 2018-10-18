package com.nmssdmf.knowledge.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.nmssdmf.customerviewlib.entity.MultiItemEntity;
import com.nmssdmf.knowledge.BR;

import java.util.List;

/**
 * Created by ${nmssdmf} on 2018/10/18 0018.
 */

public class KnowledgeBean extends BaseObservable implements MultiItemEntity{

    public static final int TYPE_GROUP = 0;
    public static final int TYPE_ITEM = 1;

    private int type;//0: group  1: item

    private String groupTitle;

    private String itemTitle;

    private List<KnowledgeBean> items;

    @Bindable
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }

    @Bindable
    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
        notifyPropertyChanged(BR.groupTitle);
    }

    @Bindable
    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
        notifyPropertyChanged(BR.itemTitle);
    }

    public List<KnowledgeBean> getItems() {
        return items;
    }

    public void setItems(List<KnowledgeBean> items) {
        this.items = items;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
