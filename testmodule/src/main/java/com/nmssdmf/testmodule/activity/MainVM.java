package com.nmssdmf.testmodule.activity;

import com.nmssdmf.commonlib.viewmodel.BaseVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${nmssdmf} on 2018/10/16 0016.
 */

public class MainVM extends BaseVM {

    private List<MainBean> list = new ArrayList<>();
    private MainCB cb;

    /**
     * 不需要callback可以传null
     *
     * @param callBack
     */
    public MainVM(MainCB callBack) {
        super(callBack);
        this.cb = callBack;
    }

    public void initList() {
        list.add(new MainBean("多线程文件下载"));
        list.add(new MainBean("rxjava"));
        list.add(new MainBean("注解"));
        list.add(new MainBean("四大组件之Service"));
        list.add(new MainBean("四大组件之BroadCastReceiver"));
        list.add(new MainBean("AsyncTask"));
        list.add(new MainBean("四大组件之ContentProvider"));
        list.add(new MainBean("四大组件之ContentProvider进程"));
        list.add(new MainBean("自定义view"));
    }

    public List<MainBean> getList() {
        return list;
    }

    public void setList(List<MainBean> list) {
        this.list = list;
    }
}
