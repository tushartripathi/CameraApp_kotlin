package com.artistecomm.Image

import android.app.Activity
import android.graphics.Bitmap
import android.os.Environment
import android.os.SystemClock
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class SaveImage {

    //Saving image to external storage
    companion object{
        //THis methid will save the image
        @Throws(IOException::class)
        fun saveFile(myActivity: Activity, bitmap: Bitmap):File {

            val myfile:File?
            //1) Check if external storage is avaible
            val externalStorageSpace = Environment.getExternalStorageState()
            if(externalStorageSpace == Environment.MEDIA_MOUNTED) // Check for storage resource
            {
                //resource exist
                val pictureDirectory :File?= myActivity.getExternalFilesDir("ColorAppPicture")
                //@param - ColorAppPicture = is Folder name (directory)
                //Creating date object to get date
                // File name
                myfile = File(pictureDirectory!!.absolutePath+getUniqueFileName())

                //Check for space
                var remainingSpace:Long = pictureDirectory?.getFreeSpace()
                var requiredSpace: Int = bitmap.getByteCount();

                if((requiredSpace * 1.8 ) < remainingSpace)
                {
                    try {
                        var fileOutPut  = FileOutputStream(myfile)
                        var isSuccessful:Boolean =  bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutPut)
                        if(isSuccessful)
                        {
                            return myfile
                        }
                        else
                            throw IOException("Image is not saved to storage")
                    }
                    catch (e: IOException)
                    {
                        throw IOException("Saving to storage went wrong")
                    }
                }
                else
                {
                    throw IOException("No Space in external storage")
                }
            }
            else
            {
                //NO external device availbel
                throw IOException("This device dont have external storage")
            }
        }

        fun getUniqueFileName():String
        {
            //Creating date object to get date
            var date: Date = Date()
            var elapsedTime:Long = SystemClock.elapsedRealtime()
            return "/"+date+"_"+elapsedTime+".png" // File name
        }
    }
}

