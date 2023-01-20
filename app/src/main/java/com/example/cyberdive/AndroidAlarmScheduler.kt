package com.example.cyberdive

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.cyberdive.data.AlarmItem
import java.time.ZoneId

class AndroidAlarmScheduler (private val context: Context) : AlarmScheduler {

    private val TAG = "MyActivity"
    private  val alarmManager = context.getSystemService(AlarmManager::class.java)
    override fun scheduleTimer(item: AlarmItem) {

//        Log.i(TAG, "MyClass.getView() — I AM HERE")
        val intent  = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA-MESSAGE", item.message)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            item.time.atZone(ZoneId.systemDefault()).toEpochSecond()*1000,
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )

        Log.v(TAG, "MyClass.getView() — I AM HERE ALSO ${item.time}")

    }


}