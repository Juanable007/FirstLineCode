package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.sql.SQLOutput;

public class MyService extends Service {
    private DownloadBinder mBinder = new DownloadBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
//        在服务创建的时候使用
        System.out.println("oncreate ----------");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        会在每次启动服务的时候使用
        Log.d("0","onStartCommand");


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("1","新线程创建");
                    Thread.sleep(3000);
                    Log.d("2","新线程执行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Log.d("3","执行结束");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
//        会在服务销毁的时候使用
        System.out.println("ONDestroy----------");

        super.onDestroy();
    }

    class DownloadBinder extends Binder {
        public void startDownLoad() {

            System.out.println("startDownLoad");
        }

        public void getProcess() {

            System.out.println("getProcess");
        }


        public IBinder onBinder(Intent intent) {

            return mBinder;
        }
    }
}