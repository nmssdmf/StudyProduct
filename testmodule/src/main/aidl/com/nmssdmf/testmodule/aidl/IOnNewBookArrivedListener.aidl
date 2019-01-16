// IOnNewBookArrivedListener.aidl
package com.nmssdmf.testmodule.aidl;
import com.nmssdmf.testmodule.aidl.Book;

// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
