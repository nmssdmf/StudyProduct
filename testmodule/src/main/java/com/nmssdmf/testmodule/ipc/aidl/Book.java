package com.nmssdmf.testmodule.ipc.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Book.java是一个表示图书信息的类，它实现了Parcelable接口，因为Book再AIDL中用到了，所以必须要键一个
 *      同名的AIDL文件Book.aidl
 * Book.aidl是Book类在AIDL中的声明。并且该文件的包名，需要跟Book.java的路径一样，并且有移动过文件
 *      的话，package com.nmssdmf.testmodule.ipc.aidl;这个package需要手动修改，不然会报错
 * IBookManager.aidl是定义的一个接口,里面有两个方法
 *      getBookList：用于从远程服务端获取图书列表
 *      addBook：往图书列表中添加一本书
 *
 */
public class Book implements Parcelable {
    public int bookId;
    public String bookName;

    public Book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    protected Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }
}
