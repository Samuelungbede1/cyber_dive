package com.example.cyberdive

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class Dialogue : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dialogue)


        val builder = AlertDialog.Builder(this)
        builder.setMessage("Time up!!!").setCancelable(
            false
        ).setPositiveButton(
            "Yes"
        ) { dialog, id -> dialog.cancel() }.setNegativeButton(
            "No"
        ) { dialog, id -> dialog.cancel() }
        val alert = builder.create()
        alert.show()

//        val builder: AlertDialog.Builder = Builder(this)
//        builder
//            .setTitle("Test")
//            .setMessage("Are you sure you want to exit?")
//            .setCancelable(false)
//            .setPositiveButton("Yes",
//                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
//            .setNegativeButton("No",
//                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
//        val alert: AlertDialog = builder.create()
//        alert.show()
    }
}