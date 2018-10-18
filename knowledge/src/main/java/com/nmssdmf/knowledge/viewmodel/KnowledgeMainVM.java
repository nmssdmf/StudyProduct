package com.nmssdmf.knowledge.viewmodel;

import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.knowledge.bean.KnowledgeBean;
import com.nmssdmf.knowledge.callback.KnowledgeMainCB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${nmssdmf} on 2018/10/18 0018.
 */

public class KnowledgeMainVM extends BaseVM{
    private List<KnowledgeBean> list = new ArrayList<>();
    /**
     * 不需要callback可以传null
     *
     * @param callBack
     */
    public KnowledgeMainVM(KnowledgeMainCB callBack) {
        super(callBack);
    }

    public List<KnowledgeBean> getList() {
        return list;
    }

    public void setList(List<KnowledgeBean> list) {
        this.list = list;
    }
}
