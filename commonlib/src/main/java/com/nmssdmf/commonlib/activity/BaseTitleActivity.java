package com.nmssdmf.commonlib.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.nmssdmf.commonlib.R;
import com.nmssdmf.commonlib.databinding.ActivityBaseTitleBinding;


/**
 * Create by huscarter@163.com on 7/11/17
 * <p>
 * Actvity 具有标题栏的基类
 * <p>
 * 此类在BaseActivity的基础上增加了toolbar的设置
 */
public abstract class BaseTitleActivity extends BaseActivity {
    protected ActivityBaseTitleBinding baseBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_title);

        initTitleView();
    }

    private void initTitleView(){

        baseBinding.tTitle.setTitle(setTitle());
        baseBinding.tTitle.setNavigationIcon(getDefaultNavigationIcon());
        setNavigationClickListener();
    }

    public void setTitle(String title) {
        baseBinding.tTitle.setTitle(title);
    }

    /**
    * @description 默认的NavigationIcon
    * @author nmssdmf
    * @date 2018/10/15 0015 17:59
    * @params
    * @return
    */
    private int getDefaultNavigationIcon(){
        return R.drawable.ic_arrow_back;
    }

    /**
    * @description 去除Navigation
    * @author nmssdmf
    * @date 2018/10/15 0015 17:59
    * @params
    * @return
    */
    protected void hideNavigation(){
        baseBinding.tTitle.setNavigationIcon(null);
    }

    /**
    * @description 设置Navigation点击事件
    * @author nmssdmf
    * @date 2018/10/15 0015 18:00
    * @version v3.2.1
    * @params 
    * @return 
    */
    private void setNavigationClickListener() {
        baseBinding.tTitle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * <p>设置顶部的标题，之所以单独提供抽象方法，是考虑到程序有可能不使用toolbar设置标题（标题需要居中）。</p>
     * <p>toolbar.refreshTitle(refreshTitle()); 靠左标题</p>
     * <p>tvTitle.setText(refreshTitle()); 剧中标题/默认</p>
     *
     * @return title
     */
    public abstract String setTitle();

    /**
     * 除了title之外的view初始化
     */
    public abstract void initContent(Bundle savedInstanceState);
}
