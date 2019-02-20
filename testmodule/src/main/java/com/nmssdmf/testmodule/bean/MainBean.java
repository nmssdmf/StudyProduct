package com.nmssdmf.testmodule.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by ${nmssdmf} on 2018/10/16 0016.
 */

public class MainBean extends BaseObservable{
    private String icon;
    private String name;

    public MainBean(){

    }

    public MainBean(String name){
        this.name = name;
    }

    @Bindable
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
        notifyPropertyChanged(BR.icon);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
