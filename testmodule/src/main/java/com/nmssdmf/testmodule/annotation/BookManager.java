package com.nmssdmf.testmodule.annotation;

/**
 * Created by ${nmssdmf} on 2019/3/8 0008.
 */

@Book(desc = "文学", author = "大妈", page = 300)
public class BookManager {
    @Book(desc = "小说", author = "小马", page = 100)
    public String getBookName(){
        return "小马邮寄";
    }
}
