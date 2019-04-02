package com.nmssdmf.testmodule.designmode.builder;

/**
 * 把Computer分成三个部分，Board，Display，OS
 */
public abstract class Computer {
    protected String mBoard;
    protected String mDisplay;
    protected String mOs;

    protected Computer(){

    }

    public void setBoard(String board){
        mBoard = board;
    }

    public void setDisplay(String display){
        mDisplay = display;
    }

    public abstract void setOS();

    @Override
    public String toString() {
        return "Computer [mBoard=" + mBoard + ",mDisplay=" + mDisplay + ",Os=" + mOs +"]";
    }
}
