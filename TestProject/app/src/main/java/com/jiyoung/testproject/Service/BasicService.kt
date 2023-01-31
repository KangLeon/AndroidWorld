package com.jiyoung.testproject.Service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class BasicService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("BasicService", "onCreate executed")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}