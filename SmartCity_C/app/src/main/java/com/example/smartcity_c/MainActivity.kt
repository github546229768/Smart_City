package com.example.smartcity_c

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.smartcity_c.network.IpSetting
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{
        const val WRITE_EXTERNAL_STORAGE = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val findNavController = findNavController(R.id.fragment)
        val build = AppBarConfiguration.Builder(bottomNavigationView.menu).build()
        NavigationUI.setupWithNavController(bottomNavigationView, findNavController)
        NavigationUI.setupActionBarWithNavController(this,findNavController,build)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            //请求权限对话框
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_EXTERNAL_STORAGE)

        hide()
    }
    private fun hide() {
        supportActionBar?.hide()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode != WRITE_EXTERNAL_STORAGE)
            Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show()
    }
}