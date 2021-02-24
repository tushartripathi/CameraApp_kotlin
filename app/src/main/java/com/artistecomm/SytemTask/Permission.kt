package com.artistecomm.SytemTask

import android.Manifest
import android.Manifest.permission_group.STORAGE
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult

import androidx.core.content.ContextCompat


class Permission
{
    companion object{
        fun getPermission(context: Context):Boolean{

            var permissionResult= ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            if(permissionResult == PackageManager.PERMISSION_GRANTED)
            {
                var packageManager :PackageManager= context.getPackageManager()
                if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
                {
                    return true
                }
                else
                    return false
            }
            else
            {
                ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.CAMERA), 1)
            }
            return false
        }
        fun getStoragePermission(context: Context):Boolean{

            var permissionResult= ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            if(permissionResult == PackageManager.PERMISSION_GRANTED)
            {
//                var packageManager :PackageManager= context.getPackageManager()
//                if(packageManager.hasSystemFeature(PackageManager.WRITE_EXTERNAL_STORAGE))
//                {
//                    return true
//                }
//                else
//                    return false
            }
            else
            {
                ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 2000)
            }
            return false
        }


    }
}