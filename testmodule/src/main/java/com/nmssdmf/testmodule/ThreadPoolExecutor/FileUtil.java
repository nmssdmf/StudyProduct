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
     * @param dLength     平均一段内容的长度,如：13M的文件，按照5M一部分下载，则每部分为5M
     */
    public synchronized static void appendFileWithInstram(String fileName, InputStream inputStream, long start, long dLength) {
        File file = new File(fileName);
        RandomAccessFile randomAccessFile = null;
        JLog.d(TAG, "Thread.currentThread().getId()333 = " + Thread.currentThread().getId());
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            randomAccessFile = new RandomAccessFile(file, "rw");
            long length = randomAccessFile.length();
            final long M = 1024;
            byte[] bytes = new byte[1024];
            int k = 0;
            long seek = 0;
            long total = 0;
            for (; ; ) {
                int readCount = inputStream.read(bytes);
                total += readCount;
                JLog.d(TAG,  "total = " + total );
                if (readCount == -1) {
                    JLog.d(TAG, "readCount = " + readCount  );
                    inputStream.close();
                    break;
                }
                if (length == 0) {//还未写入过
                    randomAccessFile.seek(k * M);
                    randomAccessFile.write(bytes);
                    ArrayList<Long> list = PreferenceUtil.getSerializables(fileName);
                    if (list != null && list.size() > 0 && !list.contains(start)) {
                        list.add(start);
                        PreferenceUtil.setSerializables(fileName, list);
                    }
//                    JLog.d(TAG, "1第" + k + "次写入");
                } else {
                    ArrayList<Long> list = PreferenceUtil.getSerializables(fileName);
                    if (list != null && list.size() > 0 && !list.contains(start)) {
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
                            seek = length;
//                            randomAccessFile.seek(length + k * M);
                        } else {
                            list.add(j, start);
                            seek = dLength * j;
//                            randomAccessFile.seek(dLength * j + k * M);
                        }
//                        randomAccessFile.write(bytes);
                    }
                    randomAccessFile.seek(seek + k * M);
                    randomAccessFile.write(bytes);
//                    JLog.d(TAG, "2第" + k + "次写入");
                }
                k += 1;
//                JLog.d(TAG, "第" + k + "次写入");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
