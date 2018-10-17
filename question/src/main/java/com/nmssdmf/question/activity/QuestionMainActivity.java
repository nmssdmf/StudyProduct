package com.nmssdmf.question.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.nmssdmf.commonlib.activity.BaseTitleActivity;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.question.R;
import com.nmssdmf.question.databinding.ActivityQuestionMainBinding;

public class QuestionMainActivity extends BaseTitleActivity {
    private final String TAG = QuestionMainActivity.class.getSimpleName();
    private ActivityQuestionMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_main);
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
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_question_main, null, false);
        return binding;
    }
}
