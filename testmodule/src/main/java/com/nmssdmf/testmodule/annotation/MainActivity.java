package com.nmssdmf.testmodule.annotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nmssdmf.testmodule.R;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        analysis();
    }

    public void analysis() {
        try {
            Class c = Class.forName("com.nmssdmf.testmodule.annotation.BookManager");

            boolean isExist = c.isAnnotationPresent(Book.class);
            if (isExist) {
                Book book = (Book) c.getAnnotation(Book.class);
                Log.d(TAG, "author = " + book.author() + "; desc = " + book.desc()+ "; page = " + book.page());
                Book method = c.getMethod("getBookName").getAnnotation(Book.class);
                Log.d(TAG, "author = " + method.author() + "; desc = " + method.desc()+ "; page = " + method.page());
                Log.d(TAG, "getBookName = "+c.getMethod("getBookName").invoke(null));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
