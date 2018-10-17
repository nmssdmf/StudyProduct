package com.nmssdmf.testmodule.callback;

import com.nmssdmf.commonlib.callback.BaseCallBack;
import com.nmssdmf.testmodule.bean.MainBean;

import java.util.List;

/**
 * Created by ${nmssdmf} on 2018/10/16 0016.
 */

public interface MainCB extends BaseCallBack {
    void refreshAdapter(boolean isRefresh, List<MainBean> list);
}
