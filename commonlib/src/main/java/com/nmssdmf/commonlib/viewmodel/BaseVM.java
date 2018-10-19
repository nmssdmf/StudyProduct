package com.nmssdmf.commonlib.viewmodel;

import com.nmssdmf.commonlib.callback.BaseCB;


/**
 * Created by ${nmssdmf} on 2018/7/4 0004.
 * 综合BaseActivityVM和BaseFragmentVM
 */

public abstract class BaseVM {

    /**
     * 基础view的回调
     */
    protected BaseCB baseCallBck;

    /**
     * 不需要callback可以传null
     * @param callBack
     */
    public BaseVM(BaseCB callBack) {
        this.baseCallBck = callBack;
        registerRxBus();
    }

    /**
     * 注册rxbus
     */
    public void registerRxBus(){}

    /**
     * 注销rxbus
     */
    public void unRegisterRxBus(){}

}
