package com.nmssdmf.commonlib.callback;

import java.util.List;

/**
 * Created by ${nmssdmf} on 2018/10/19 0019.
 */

public interface BaseTitleRecyclerViewCB extends BaseCB {

    void refreshAdapter(boolean isRefresh, List dataList);
}
