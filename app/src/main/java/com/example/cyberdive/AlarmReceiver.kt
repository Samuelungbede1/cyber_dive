package com.example.cyberdive

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.cyberdive.views.SetAppUsageScreen


class AlarmReceiver: BroadcastReceiver() {

    private val TAG = "MyActivity"

    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA-MESSAGE") ?: return
        Log.v(TAG, "MyClass.getView() —CALLED AT ONRECEIVE $message")

        val i: Intent = Intent(context!!.applicationContext, Dialogue::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context!!.startActivity(i)

    }
}