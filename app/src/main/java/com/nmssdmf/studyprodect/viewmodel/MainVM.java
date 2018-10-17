package com.nmssdmf.studyprodect.viewmodel;

import android.view.View;

import com.nmssdmf.commonlib.callback.BaseCallBack;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.studyprodect.config.ClassNameConfig;

/**
 * Created by ${nmssdmf} on 2018/10/17 0017.
 */

public class MainVM extends BaseVM {

    /**
     * 不需要callback可以传null
     *
     * @param callBack
     */
    public MainVM(BaseCallBack callBack) {
        super(callBack);
    }

    public void tvKnowledgeClick(View view) {
        baseCallBck.doIntentClassName(ClassNameConfig.KNOWLEDGE_MAIN_ACTIVITY, null);
    }

    public void tvStudioClick(View view) {
        baseCallBck.doIntentClassName(ClassNameConfig.STUDIO_MAIN_ACTIVITY, null);
    }

    public void tvQuestionClick(View view) {
        baseCallBck.doIntentClassName(ClassNameConfig.QUESTION_MAIN_ACTIVITY, null);
    }
}
