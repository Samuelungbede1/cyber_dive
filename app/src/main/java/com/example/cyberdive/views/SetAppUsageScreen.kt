package com.example.cyberdive.views

import android.app.AlertDialog
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cyberdive.AndroidAlarmScheduler
import com.example.cyberdive.R
import com.example.cyberdive.data.AlarmItem
import com.example.cyberdive.databinding.FragmentSetAppUsageScreenBinding
import java.time.LocalDateTime


class SetAppUsageScreen : Fragment(R.layout.fragment_set_app_usage_screen) {
    private  lateinit var binding: FragmentSetAppUsageScreenBinding
    var ctx: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ctx = requireContext()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetAppUsageScreenBinding.bind(view)
        val scheduler = AndroidAlarmScheduler(requireContext())
        var alarmItem : AlarmItem? = null



        binding.btnSetTime.setOnClickListener {
            alarmItem = AlarmItem(
                LocalDateTime.now().plusSeconds(
                    binding.etTime.text.toString().toLong()
                ),
                "CHATGPT"
            )
            alarmItem?.let (scheduler::scheduleTimer)
            binding.etTime.setText("DONE")
        }


    }


    fun showAlert(context: Context){
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure you want to exit?").setCancelable(
            false
        ).setPositiveButton(
            "Yes"
        ) { dialog, id -> dialog.cancel() }.setNegativeButton(
            "No"
        ) { dialog, id -> dialog.cancel() }
        val alert = builder.create()
        alert.show()
    }


    fun getTimeSpent(context: Context, packageName: String?, beginTime: Long, endTime: Long): HashMap<String, Int?>? {
        
        var currentEvent: UsageEvents.Event
        val allEvents: MutableList<UsageEvents.Event> = ArrayList()
        val appUsageMap: HashMap<String, Int?> = HashMap()
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val usageEvents = usageStatsManager.queryEvents(beginTime, endTime)
        while (usageEvents.hasNextEvent()) {
            currentEvent = UsageEvents.Event()
            usageEvents.getNextEvent(currentEvent)
            if (currentEvent.packageName == packageName || packageName == null) {
                if (currentEvent.eventType == UsageEvents.Event.ACTIVITY_RESUMED
                    || currentEvent.eventType == UsageEvents.Event.ACTIVITY_PAUSED
                ) {
                    allEvents.add(currentEvent)
                    val key = currentEvent.packageName
                    if (appUsageMap[key] == null) appUsageMap[key] = 0
                }
            }
        }
        for (i in 0 until allEvents.size - 1) {
//            val E0 = allEvents[i]
//            val E1 = allEvents[i + 1]

            val lastEvent: UsageEvents.Event = allEvents.get(allEvents.size - 1)
            if (lastEvent.eventType == UsageEvents.Event.ACTIVITY_RESUMED) {
                var diff = System.currentTimeMillis().toInt() - lastEvent.timeStamp
                    .toInt()
                diff /= 1000
                var prev: Int? = appUsageMap.get(lastEvent.packageName)
                if (prev == null) prev = 0
                appUsageMap.put(lastEvent.packageName, prev + diff)
            }
        }
        return appUsageMap
    }


//    val lastEvent: UsageEvents.Event = allEvents.get(allEvents.size - 1)
//    if (lastEvent.eventType == UsageEvents.Event.ACTIVITY_RESUMED) {
//        var diff = System.currentTimeMillis().toInt() - lastEvent.timeStamp
//            .toInt()
//        diff /= 1000
//        var prev: Int = appUsageMap.get(lastEvent.packageName)
//        if (prev == null) prev = 0
//        appUsageMap.put(lastEvent.packageName, prev + diff)
//    }





}