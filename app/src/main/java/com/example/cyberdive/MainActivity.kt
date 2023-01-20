package com.example.cyberdive

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val OVERLAY_REQUEST_CODE = 100
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                if ("xiaomi" == Build.MANUFACTURER.toLowerCase(Locale.ROOT)) {
                    val intent = Intent("miui.intent.action.APP_PERM_EDITOR")
                    intent.setClassName(
                        "com.miui.securitycenter",
                        "com.miui.permcenter.permissions.PermissionsEditorActivity"
                    )
                    intent.putExtra("extra_pkgname", packageName)
                    AlertDialog.Builder(this)
                        .setTitle("Please Enable the additional permissions")
                        .setMessage("You will not receive notifications while the app is in background if you disable these permissions")
                        .setPositiveButton("Go to Settings",
                            DialogInterface.OnClickListener { dialog, which -> startActivity(intent) })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setCancelable(false)
                        .show()
                } else {
                    val overlaySettings = Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse(
                            "package:$packageName"
                        )
                    )
                    startActivityForResult(overlaySettings, OVERLAY_REQUEST_CODE)
                }
            }
        }
//
//        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
//            val sb = StringBuilder()
//            sb.append("package:")
//            sb.append(packageName)
//            startActivityForResult(
//                Intent(
//                    "android.settings.action.MANAGE_OVERLAY_PERMISSION",
//                    Uri.parse(sb.toString())
//                ), 123
//            )
//        }

    }
}