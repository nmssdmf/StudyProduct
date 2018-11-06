package com.nmssdmf.question.activity;

import android.os.Bundle;

import com.nmssdmf.commonlib.activity.BaseTitleActivity;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.question.R;
import com.nmssdmf.question.databinding.ActivityQuestionMainBinding;

public class QuestionMainActivity extends BaseTitleActivity {
    private final String TAG = QuestionMainActivity.class.getSimpleName();
    private ActivityQuestionMainBinding binding;

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
    public int getContentViewId() {
        return R.layout.activity_question_main;
    }
}
