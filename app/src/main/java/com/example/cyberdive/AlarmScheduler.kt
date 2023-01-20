package com.example.cyberdive

import com.example.cyberdive.data.AlarmItem

interface AlarmScheduler {

    fun scheduleTimer ( item : AlarmItem)
}