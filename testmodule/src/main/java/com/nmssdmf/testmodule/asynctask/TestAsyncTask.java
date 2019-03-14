package com.nmssdmf.testmodule.asynctask;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by ${nmssdmf} on 2019/3/14 0014.
 */

public class TestAsyncTask extends AsyncTask<String, Integer, String> {

    private final String TAG = TestAsyncTask.class.getSimpleName();

    //onPreExecute用于异步处理前的操作
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute");
    }

    //在doInBackground方法中进行异步任务的处理.
    @Override
    protected String doInBackground(String... strings) {
        Log.d(TAG, "doInBackground ");
        for (int i =0; i< strings.length; i++) {
            Log.d(TAG, "string = " + strings[i]);
            publishProgress(i);
        }
        return "B";
    }

    //onPostExecute用于UI的更新.此方法的参数为doInBackground方法返回的值.
    @Override
    protected void onPostExecute(String o) {
        Log.d(TAG, "onPostExecute " + o);
        super.onPostExecute(o);
    }

    //在调用publishProgress(Progress... values)时，此方法被执行，直接将进度信息更新到UI组件上。
    @Override
    protected void onProgressUpdate(Integer[] values) {
        Log.d(TAG, "onProgressUpdate ");
        for (int i =0; i< values.length; i++) {
            Log.d(TAG, "value = " + values[i]);
        }
        super.onProgressUpdate(values);
    }
}
