package com.nmssdmf.knowledge.activity;

import android.os.Bundle;

import com.nmssdmf.commonlib.activity.BaseTitleRecyclerViewActivity;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.knowledge.adapter.KnowledgeMainAdapter;
import com.nmssdmf.knowledge.callback.KnowledgeMainCB;
import com.nmssdmf.knowledge.viewmodel.KnowledgeMainVM;

public class KnowledgeMainActivity extends BaseTitleRecyclerViewActivity implements KnowledgeMainCB{
    private final String TAG = KnowledgeMainActivity.class.getSimpleName();

    private KnowledgeMainVM vm;
    private KnowledgeMainAdapter adapter;

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public BaseVM initViewModel() {
        vm = new KnowledgeMainVM(this);
        return vm;
    }

    @Override
    public String setTitle() {
        return "Knowledge";
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        adapter = new KnowledgeMainAdapter(vm.getList());
    }

}
