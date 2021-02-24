package com.artistecomm.Image

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.core.content.FileProvider
import java.io.File


class ShareImage {

    companion object {
        fun shareFile(context: Context, bitmap: Bitmap)
        {
            var myPictureFile:File = SaveImage.saveFile(context as Activity, bitmap)
            //var uri: Uri = Uri.fromFile(myPictureFile)
            val uri = FileProvider.getUriForFile(context, context.getApplicationContext().packageName.toString() + ".provider", myPictureFile)
            var shareInent: Intent?= Intent(Intent.ACTION_SEND)
            shareInent?.setType("text/plain")
            shareInent?.putExtra(Intent.EXTRA_SUBJECT, "This picture is sent by me")
            shareInent?.putExtra(Intent.EXTRA_STREAM, uri)
            shareInent?.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(Intent.createChooser(shareInent, "share with other apps"))
           // Toast.makeText(context,"Class DOne....", Toast.LENGTH_SHORT).show()
        }

    }
}