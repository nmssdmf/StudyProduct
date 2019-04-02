package com.nmssdmf.testmodule.designmode.builder;

public class Macbook extends Computer {

    protected Macbook(){

    }

    @Override
    public void setOS() {
        mOs = "Mac OS X 10.10";
    }
}
