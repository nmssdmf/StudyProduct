// IBookManager.aidl
package com.nmssdmf.testmodule.ipc.aidl;

/*
* 自定义的Book类，尽管处于同一个包中，仍然需要导入
*/
import com.nmssdmf.testmodule.ipc.aidl.Book;
import com.nmssdmf.testmodule.ipc.aidl.IOnNewBookArrivedListener;
// Declare any non-default types here with import statements
/*
* AIDL中除了基本类型，其他类型必须标注方向
*   in：表示输入参数
*   out：表示输出参数
*   inout：表示输入输出参数
* AIDL接口只支持方法，不支持声明静态常量
*/
interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unRegisterListener(IOnNewBookArrivedListener listener);
}
