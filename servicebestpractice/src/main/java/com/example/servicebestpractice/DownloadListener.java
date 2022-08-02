package com.example.servicebestpractice;

public interface DownloadListener {

    //通知当前执行进度
    void onProgress(int progress);

    //通知下载成功
    void onSuccess();

    //通知下载失败
    void onFail();

    //下载暂停
    void onPause();

    //下载关闭
    void onCanceled();


}
