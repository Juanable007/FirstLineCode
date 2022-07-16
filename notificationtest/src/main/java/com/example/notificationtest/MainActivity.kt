package com.example.notificationtest

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        步骤
        /*   1. 获取一个通知的服务
             2.创建一个通知渠道(context, 渠道ID,重要性级别)
             3. 利用AndroidX,兼容性API创建Notification通知(对应通知渠道),并设置相应的基本属性,例如标题,图标等,最后build()

             */

        //    1. 获取一个通知的服务
        val manger = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            2.创建一个通知渠道(context, 渠道ID,重要性级别)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //版本判断,Andorid 8.0 以上的专用APi
            val channel =
                NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_HIGH)

            manger.createNotificationChannel(channel)
        }

        sendNotice.setOnClickListener {
            println("sendNotice 部分执行!!!!")
            //跳转功能
            val intent = Intent(this, NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, FLAG_IMMUTABLE)


            val notification = NotificationCompat.Builder(
                this,
                "normal"
            ).setContentTitle("This is content title")
                .setContentText("This is content text")
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large_icon))
                .setContentIntent(pi)
                .setAutoCancel(true)//自动关闭
                .build()

            manger.notify(1, notification)


        }

    }
}