package com.nmssdmf.knowledge.activity;

import com.nmssdmf.commonlib.activity.BaseTitleRecyclerViewActivity;
import com.nmssdmf.commonlib.viewmodel.BaseTitleRecyclerViewVM;
import com.nmssdmf.customerviewlib.databindingbase.BaseDataBindingAdapter;
import com.nmssdmf.knowledge.adapter.KnowledgeMainAdapter;
import com.nmssdmf.knowledge.callback.KnowledgeMainCB;
import com.nmssdmf.knowledge.viewmodel.KnowledgeMainVM;

import java.util.List;

public class KnowledgeMainActivity extends BaseTitleRecyclerViewActivity implements KnowledgeMainCB{
    private final String TAG = KnowledgeMainActivity.class.getSimpleName();

    private KnowledgeMainVM vm;
    private KnowledgeMainAdapter adapter;

    @Override
    public String getTAG() {
        return TAG;
    }



    @Override
    public String setTitle() {
        return "Knowledge";
    }


    @Override
    public BaseTitleRecyclerViewVM initTitleRecyclerViewViewModel() {
        vm = new KnowledgeMainVM(this);
        return vm;
    }


    @Override
    public BaseDataBindingAdapter initAdapter(List list) {
        adapter = new KnowledgeMainAdapter(list);
        return adapter;
    }
}
