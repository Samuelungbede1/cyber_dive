package com.example.cyberdive.views

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cyberdive.R
import com.example.cyberdive.adapter.InstalledAppAdapter
import com.example.cyberdive.data.ApplicationData
import com.example.cyberdive.databinding.FragmentInstalledAppScreenBinding
import java.util.*
import kotlin.collections.ArrayList


class InstalledAppScreen : Fragment(R.layout.fragment_installed_app_screen) {
    private lateinit var binding : FragmentInstalledAppScreenBinding
    private lateinit var installedApplications : ArrayList<ApplicationData>
    private lateinit var appName: String
//    private lateinit var servicedPart: String



    private lateinit var applicationsAdapter: InstalledAppAdapter
    private lateinit var applicationsRecyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInstalledAppScreenBinding.bind(view)

        installedApplications = ArrayList<ApplicationData>()
        applicationsRecyclerView = binding.rvAppRecyclerview


        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        applicationsRecyclerView.setLayoutManager(staggeredGridLayoutManager)
        applicationsAdapter = InstalledAppAdapter(installedApplications)


        val pm: PackageManager = requireContext().getPackageManager()
        installedApplications = ArrayList<ApplicationData>()
        appName = ""

            installedApplications.clear()
            val packs = pm.getInstalledPackages(0)
            for (i in packs.indices) {
                val p = packs[i]
                if (!isSystemPackage(p)) {
                    appName = p.applicationInfo.loadLabel(pm).toString()
                    var application = ApplicationData(appName)
                    installedApplications.add(application)
                    applicationsAdapter.addApplication(installedApplications)
                    applicationsRecyclerView.adapter = applicationsAdapter
                    binding.tvNoTransaction.text = installedApplications.size.toString()
                }
            }
        }


    private fun isSystemPackage(pkgInfo: PackageInfo): Boolean {
        return pkgInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }


    }