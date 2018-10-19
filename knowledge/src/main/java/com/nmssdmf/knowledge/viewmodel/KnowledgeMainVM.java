package com.nmssdmf.knowledge.viewmodel;

import com.nmssdmf.commonlib.viewmodel.BaseTitleRecyclerViewVM;
import com.nmssdmf.knowledge.bean.KnowledgeBean;
import com.nmssdmf.knowledge.callback.KnowledgeMainCB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${nmssdmf} on 2018/10/18 0018.
 */

public class KnowledgeMainVM extends BaseTitleRecyclerViewVM {
    /**
     * 不需要callback可以传null
     *
     * @param callBack
     */
    public KnowledgeMainVM(KnowledgeMainCB callBack) {
        super(callBack);
    }

    @Override
    public void initData(boolean isRefresh) {
        if (isRefresh)
            list.clear();
        int size = list.size();
        List<KnowledgeBean> knowledgeBeans = new ArrayList<>();
        for (int i = size; i < size + 10; i++) {
            KnowledgeBean groupBean = new KnowledgeBean();
            groupBean.setGroupTitle("第" + i + "组");
            groupBean.setItems(new ArrayList<KnowledgeBean>());
            for (int j = 0; j < 5; j++) {
                KnowledgeBean itemBean = new KnowledgeBean();
                groupBean.setItemTitle("第" + j + "个成员");
                groupBean.getItems().add(itemBean);
            }
            knowledgeBeans.add(groupBean);
        }
        baseTitleRecyclerViewCB.refreshAdapter(isRefresh, knowledgeBeans);
    }
}
