package com.nmssdmf.knowledge.viewmodel;

import com.nmssdmf.commonlib.viewmodel.BaseTitleRecyclerViewVM;
import com.nmssdmf.knowledge.callback.KnowledgeMainCB;
import com.nmssdmf.knowledge.util.XmlUtil;

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
//        if (isRefresh)
//            list.clear();
//        int size = list.size();
//        List<KnowledgeBean> knowledgeBeans = new ArrayList<>();
//        for (int i = size; i < size + 10; i++) {
//            KnowledgeBean groupBean = new KnowledgeBean();
//            groupBean.setType(KnowledgeBean.TYPE_GROUP);
//            groupBean.setGroupTitle("第" + i + "组");
//            groupBean.setItems(new ArrayList<KnowledgeBean>());
//            for (int j = 0; j < 5; j++) {
//                KnowledgeBean itemBean = new KnowledgeBean();
//                itemBean.setType(KnowledgeBean.TYPE_ITEM);
//                itemBean.setItemTitle("第" + j + "个成员");
//                groupBean.getItems().add(itemBean);
//            }
//            knowledgeBeans.add(groupBean);
//        }
//        list.addAll(knowledgeBeans);
//        baseTitleRecyclerViewCB.refreshAdapter(isRefresh, knowledgeBeans);
    }
}
