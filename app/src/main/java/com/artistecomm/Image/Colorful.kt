package com.artistecomm.Image

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.widget.SeekBar
import androidx.annotation.RequiresApi

class Colorful(bitmap:Bitmap, redvalue:Float,greenvalue:Float,bluevalue:Float)
{
    private var bitmap: Bitmap

    private var redColorValue:Float=0f

        fun getRedColorValue():Float
        {
            return redColorValue
        }

        fun setRedColor(value:Float)
        {
            if(value>=0 && value<=1)
                redColorValue = value
        }
    private var greenColorValue:Float=0f
    fun getGreenColorValue():Float
    {
        return greenColorValue
    }

    fun setGreenColor(value:Float)
    {
        if(value>=0 && value<=1)
            greenColorValue = value
    }
    private var blueColorValue:Float=0f
    fun getBlueColorValue():Float
    {
        return blueColorValue
    }

    fun setBlueColor(value:Float)
    {
        if(value>=0 && value<=1)
            blueColorValue = value
    }

    init{
        this.bitmap = bitmap
         setRedColor(redvalue)
         setBlueColor(bluevalue)
         setGreenColor(greenvalue)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public fun returnBitmap():Bitmap
    {
        var width = bitmap.getWidth()
        var height = bitmap.getHeight()

        var bitmapConfig:Bitmap.Config = bitmap.getConfig()
        var localBitmap =   Bitmap.createBitmap(width,height,bitmapConfig)

        var row=0
        var col=0
        while(row<width)
        {
            col=0
            while(col<height)
            {
                var pixel = bitmap.getPixel(row,col)
                pixel = Color.argb(Color.alpha(pixel) *1f,
                        getRedColorValue() * Color.red(pixel),
                        getGreenColorValue() * Color.green(pixel),
                        getBlueColorValue() * Color.blue(pixel))
                localBitmap.setPixel(row,col,pixel)
                col++
            }
            row++;
        }

        return localBitmap
    }

}