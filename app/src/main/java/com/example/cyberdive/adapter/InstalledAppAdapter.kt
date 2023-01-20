package com.example.cyberdive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cyberdive.R
import com.example.cyberdive.data.ApplicationData

class InstalledAppAdapter(var application:  ArrayList<ApplicationData>): RecyclerView.Adapter<InstalledAppAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var appName = view.findViewById<TextView>(R.id.tv_app_name)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.application_layout_item,parent,false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val applicaton : ApplicationData = application[position]
        holder.appName.text = applicaton.appName
    }


    fun addApplication(listOfApps : ArrayList<ApplicationData>){
        application.clear()
        application.addAll(listOfApps)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return application.size
    }
}