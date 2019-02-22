package com.nmssdmf.testmodule.ThreadPoolExecutor;

import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.commonlib.util.PreferenceUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created by ${nmssdmf} on 2019/2/20 0020.
 * 文件读写
 */

public class FileUtil {
    private static final String TAG = FileUtil.class.getSimpleName();

    /**
     * 文件分批写入
     *
     * @param fileName 文件路径
     * @param content  需要写入的内容
     * @param start    写入的初始位置
     * @param dLength  平均一段内容的长度,如：13M的文件，按照5M一部分下载，则每部分为5M
     */
    public synchronized static void appendFile(String fileName, byte[] content, long start, long dLength) {
        File file = new File(fileName);
        RandomAccessFile randomAccessFile = null;

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            randomAccessFile = new RandomAccessFile(file, "rw");
            long length = randomAccessFile.length();
            if (length == 0) {//还未写入过
                randomAccessFile.write(content);
                ArrayList<Long> list = new ArrayList<>();
                list.add(start);
                PreferenceUtil.setSerializables(fileName, list);
                JLog.d(TAG, "第一次写入");
            } else {
                ArrayList<Long> list = PreferenceUtil.getSerializables(fileName);
                if (list != null && list.size() > 0) {
                    int j = -1;
                    for (int i = 0; i < list.size(); i++) {//比较已经写入的位置，找到准确的位置
                        if (start > list.get(i)) {
                            continue;
                        } else {
                            j = i;
                        }
                    }
                    //找到的准确的位置之后，进行定位和写入,并且储存位置
                    if (j == -1) {//最后一段
                        list.add(start);
                        randomAccessFile.seek(length);
                    } else {
                        list.add(j, start);
                        randomAccessFile.seek(dLength * j);
                    }
                    randomAccessFile.write(content);
                    JLog.d(TAG, "第" + (j + 1) + "次写入");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件分批写入
     *
     * @param fileName    文件路径
     * @param inputStream 需要写入的内容
     * @param start       写入的初始位置
     */
    public static void appendFileWithInstramWithThread(String fileName, InputStream inputStream, long start) {
        File file = new File(fileName);
        synchronized (TAG) {
            try {
                while (true) {
                    long fileLength = PreferenceUtil.getLong(fileName, 0l);
                    JLog.d("FileUtil", "fileLength = " + fileLength + "\n start = " + start);
                    if (fileLength == start) {
                        break;
                    }
                    TAG.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RandomAccessFile randomAccessFile = null;
            JLog.d(TAG, "Thread.currentThread().getId()333 = " + Thread.currentThread().getId());
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                randomAccessFile = new RandomAccessFile(file, "rw");

                final long M = 1024;
                long total = PreferenceUtil.getLong(fileName, 0l);
                for (; ; ) {
                    byte[] bytes = new byte[(int) M];
                    int readCount = inputStream.read(bytes);
                    if (readCount == 0) {
                        continue;
                    }
                    if (readCount == -1) {
                        JLog.d(TAG, "readCount = " + readCount + "\n start = " + start + "\ntotal = " + total);
                        PreferenceUtil.setLongValue(fileName, total);
                        inputStream.close();
                        randomAccessFile.close();
                        break;
                    }

                    randomAccessFile.seek(total);
                    total += readCount;
                    randomAccessFile.write(bytes, 0, readCount);
                    JLog.d(TAG, "length = " + randomAccessFile.length());
                    JLog.d(TAG, "total = " + total);
                }

                TAG.notifyAll();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件分批写入
     *
     * @param fileName    文件路径
     * @param inputStream 需要写入的内容
     * @param start       写入的初始位置
     */
    public synchronized static void appendFileWithInstram(String fileName, InputStream inputStream, long start) {
        File file = new File(fileName);
        RandomAccessFile randomAccessFile = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            randomAccessFile = new RandomAccessFile(file, "rw");
            final long M = 1024;
            byte[] bytes = new byte[(int) M];
            long seek = start;
            long total = 0;
            for (; ; ) {
                int readCount = inputStream.read(bytes);

                if (readCount == 0) {
                    continue;
                }
                if (readCount == -1) {
                    JLog.d(TAG, "readCount = " + readCount + "\n start = " + start + "\ntotal = " + total);
                    JLog.d(TAG, "fileLength = " + randomAccessFile.length()+ "\n seek = " + seek );
                    inputStream.close();
                    break;
                }
                total += readCount;
                randomAccessFile.seek(seek );
                randomAccessFile.write(bytes, 0, readCount);
                seek += readCount;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
