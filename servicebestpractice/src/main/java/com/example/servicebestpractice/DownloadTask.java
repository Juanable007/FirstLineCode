package com.example.servicebestpractice;

import android.os.AsyncTask;
import android.os.Environment;
import android.view.textclassifier.TextLinks;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.time.OffsetDateTime;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    private static final int TYPE_SUCCESS = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_PAUSED = 2;
    private static final int TYPE_CANCELED = 3;


    private DownloadListener downloadListener;
    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;

    public DownloadTask(DownloadListener listener) {
        this.downloadListener=listener;

    }


    @Override
    protected Integer doInBackground(String... parms) {
        //处理下载逻辑
        InputStream inputStream = null;
        RandomAccessFile saveFile = null;
        File file = null;
        try {
            long downloadLength = 0; //记录已经下载的长度.
            String downloadUrl = parms[0];//下载地址
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf('/'));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            if (file.exists()) {
                //判断download目录下是否已经下载了该文件
                downloadLength = file.length();//后续启用断点续传功能
            }
            //待下载文件的总长度
            long contentLength = getContentLength(downloadUrl);

            if (contentLength == 0) {
                //如果待下载文件长度为0 则返回错误信息fail
                return TYPE_FAILED;
            } else if (contentLength == downloadLength) {
                //如果当前下载的长度和文件的总字节一致 则下载成功 .
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "byte=" + downloadLength + "-")
                    .url(downloadUrl).build();

            Response response = client.newCall(request).execute();

            if (response != null) {
                inputStream = response.body().byteStream();
                saveFile = new RandomAccessFile(file, "rw");
                saveFile.seek(downloadLength);//跳过已下载的字节
                byte[] bytes = new byte[1024];
                int total = 0;
                int len;
                while ((len = inputStream.read()) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        saveFile.write(bytes, 0, len);
                        //计算已经下载的百分比
                        int progress = (int) ((total + downloadLength) * 100 / contentLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (saveFile != null) {
                    saveFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return TYPE_FAILED;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        // 更新下载进度
        int progress = values[0];
        if (progress > lastProgress) {
            downloadListener.onProgress(progress);
        }
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        //通知最后的的下载结果

        switch (integer) {
            case TYPE_SUCCESS:
                downloadListener.onSuccess();
            case TYPE_FAILED:
                downloadListener.onFail();
            case TYPE_PAUSED:
                downloadListener.onPause();
            case TYPE_CANCELED:
                downloadListener.onCanceled();
            default:
                break;
        }
        super.onPostExecute(integer);
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void canceledDownload() {
        isCanceled = true;
    }

    //
    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl).build();

        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long l = response.body().contentLength();
            response.close();
            return l;
        }
        return 0;
    }

}
