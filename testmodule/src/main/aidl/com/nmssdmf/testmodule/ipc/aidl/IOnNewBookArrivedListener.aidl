// IOnNewBookArrivedListener.aidl
package com.nmssdmf.testmodule.ipc.aidl;
import com.nmssdmf.testmodule.ipc.aidl.Book;
// Declare any non-default types here with import statements
/**
* 这个接口用于服务端主动通知客户端
*/
interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
