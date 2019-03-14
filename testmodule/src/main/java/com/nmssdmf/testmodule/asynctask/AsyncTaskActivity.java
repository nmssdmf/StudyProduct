package com.nmssdmf.testmodule.asynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nmssdmf.testmodule.R;

/**
 * AsyncTask:
 *      onPreExecute:在异步任务之前执行
 *      doInBackground:执行异步任务
 *      onPostExecute:异步任务执行返回之后执行
 *      publishProgress:在异步任务执行的过程中发送消息
 *      onProgressUpdate:接受publishProgress消息进行ui更新
 * 原理:1、创建AsyncTask的时候，会生成 InternalHandler, WorkerRunnable, FutureTask
 *      2、调用AsyncTask.execute()
 *      3、先调用 onPreExecute()方法,execute(mFuture)
 *      4、FutureTask的call方法是WorkerRunnable实现的，所以调用WorkerRunnable的call
 *      5、在WorkerRunnable的call中调用了doInBackground方法
 *      6、结束之后handler发送了一个事件，调用AsyncTask.finish方法,在finish方法中调用onPostExecute
 *
 *      1、publishProgress方法中，handler发送事件，处理调用onProgressUpdate方法
 */
public class AsyncTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestAsyncTask testAsyncTask = new TestAsyncTask();
                testAsyncTask.execute("A", "B", "C");
            }
        });

    }
}
