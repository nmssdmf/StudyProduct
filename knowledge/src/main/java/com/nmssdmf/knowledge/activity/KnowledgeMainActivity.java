package com.nmssdmf.knowledge.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.google.gson.Gson;
import com.nmssdmf.commonlib.activity.BaseTitleActivity;
import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.knowledge.R;
import com.nmssdmf.knowledge.adapter.KnowledgeMainAdapter1;
import com.nmssdmf.knowledge.callback.KnowledgeMainCB;
import com.nmssdmf.knowledge.databinding.ActivityKnowledgeMainBinding;
import com.nmssdmf.knowledge.util.XmlUtil;
import com.nmssdmf.knowledge.viewmodel.KnowledgeMainVM;

import java.util.List;

public class KnowledgeMainActivity extends BaseTitleActivity implements KnowledgeMainCB{
    private final String TAG = KnowledgeMainActivity.class.getSimpleName();

    private ActivityKnowledgeMainBinding binding;
    private KnowledgeMainVM vm;
    private KnowledgeMainAdapter1 adapter;

    @Override
    public void refreshAdapter(boolean isRefresh, List dataList) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public String setTitle() {
        return "Knowledge";
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        adapter = new KnowledgeMainAdapter1(vm.getList(), this);
        binding.elv.setAdapter(adapter);
        vm.initData(false);
        vm.getList().addAll(XmlUtil.parseXml(this));
        JLog.d(TAG, "list = " + new Gson().toJson(vm.getList()));
    }

    @Override
    public ViewDataBinding getContentRootView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_knowledge_main, null, false);
        return binding;
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public BaseVM initViewModel() {
        vm = new KnowledgeMainVM(this);
        return vm;
    }
}
