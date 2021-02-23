package com.artistecomm.Image

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi

class Colorful(bitmap:Bitmap, redvalue:Float,greenvalue:Float,bluevalue:Float)
{
    private var bitmap: Bitmap

    private var redColorValue:Float=0f
        get() = field
        set(value) {
            if(value>=0 && value<=1)
                field = value
        }
    private var greenColorValue:Float=0f
        get() = field
        set(value) {
            if(value>=0 && value<=1)
                field = value
        }
    private var blueColorValue:Float=0f
        get() = field
        set(value) {
            if(value>=0 && value<=1)
                field = value
        }

    init{
        this.bitmap = bitmap
        redColorValue=redvalue
        blueColorValue=bluevalue
        greenColorValue=greenvalue
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public fun getBitmap():Bitmap
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
                pixel = Color.argb(Color.alpha(pixel) as Float,
                        redColorValue * Color.red(pixel),
                        greenColorValue * Color.green(pixel),
                        blueColorValue * Color.blue(pixel))
                localBitmap.setPixel(row,col,pixel)
                col++
            }
            row++;
        }

        return localBitmap
    }


}