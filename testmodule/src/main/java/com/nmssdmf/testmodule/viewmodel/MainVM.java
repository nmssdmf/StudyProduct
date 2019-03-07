package com.nmssdmf.testmodule.viewmodel;

import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.testmodule.bean.MainBean;
import com.nmssdmf.testmodule.callback.MainCB;

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
        list.add(new MainBean("sort"));
        list.add(new MainBean("线程打印"));
    }

    public List<MainBean> getList() {
        return list;
    }

    public void setList(List<MainBean> list) {
        this.list = list;
    }
}
