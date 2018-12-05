package com.nmssdmf.testmodule.application;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.nmssdmf.commonlib.application.BaseApplication;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.util.IInnerImageSetter;

/**
 * Created by ${nmssdmf} on 2018/12/5 0005.
 */

public class App extends BaseApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        TangramBuilder.init(this, new IInnerImageSetter() {
            @Override
            public <IMAGE extends ImageView> void doLoadImageUrl(@NonNull IMAGE view, @Nullable String url) {

            }
        }, ImageView.class);
    }
}
