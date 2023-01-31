package com.jiyoung.testproject.Service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.jiyoung.testproject.MainActivity
import com.jiyoung.testproject.R

class BasicService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("BasicService", "onCreate executed")
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("basic_service", "前台Service通知",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this, "basic_service")
            .setContentTitle("消息标题")
            .setContentText("消息描述")
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.tiny)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.tiny))
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("BasicService","onStartCommand executed")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService","onDestroy executed")
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}