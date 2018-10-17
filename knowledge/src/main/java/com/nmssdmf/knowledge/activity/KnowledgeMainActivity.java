package com.nmssdmf.knowledge.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.nmssdmf.commonlib.activity.BaseTitleActivity;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.knowledge.R;
import com.nmssdmf.knowledge.databinding.ActivityKnowledgeMainBinding;

public class KnowledgeMainActivity extends BaseTitleActivity {
    private final String TAG = KnowledgeMainActivity.class.getSimpleName();
    private ActivityKnowledgeMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_main);
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public BaseVM initViewModel() {
        return null;
    }

    @Override
    public String setTitle() {
        return "Knowledge";
    }

    @Override
    public void initContent(Bundle savedInstanceState) {

    }

    @Override
    public ViewDataBinding getContentRootView() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_knowledge_main, null, false);
        return binding;
    }
}
