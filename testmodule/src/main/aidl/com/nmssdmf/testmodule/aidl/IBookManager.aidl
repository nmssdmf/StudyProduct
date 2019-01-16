// IBookManager.aidl
package com.nmssdmf.testmodule.aidl;

import com.nmssdmf.testmodule.aidl.Book;
import com.nmssdmf.testmodule.aidl.IOnNewBookArrivedListener;

// Declare any non-default types here with import statements

interface IBookManager {

    List<Book> getBookList();//从远程获取列表
    void addBook(in Book book);//往图书列表中插入一本书
    void registerListener(IOnNewBookArrivedListener listener);
    void unRegisterListener(IOnNewBookArrivedListener listener);
}
