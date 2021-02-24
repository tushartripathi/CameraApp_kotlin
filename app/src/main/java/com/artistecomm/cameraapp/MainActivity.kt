package com.artistecomm.cameraapp

import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.artistecomm.Image.Colorful
import com.artistecomm.Image.SaveImage
import com.artistecomm.SytemTask.Permission
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() , View.OnClickListener
{

    lateinit var colourful: Colorful

    private lateinit var takeImageBtn: Button
    private lateinit var saveImageBtn: Button
    private lateinit var imageView: ImageView
    private lateinit var redSeek: SeekBar
    private lateinit var greenSeek: SeekBar
    private lateinit var blueSeek: SeekBar
    private lateinit var redText: TextView
    private lateinit var greenText: TextView
    private lateinit var blueText: TextView

    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        takeImageBtn = findViewById(R.id.buttonTakeIamge)
        saveImageBtn = findViewById(R.id.buttonSaveTheGallery)
        imageView = findViewById(R.id.imageView)
        redSeek = findViewById(R.id.seekBar)
        greenSeek = findViewById(R.id.seekBar2)
        blueSeek = findViewById(R.id.seekBar3)
        redText = findViewById(R.id.textView)
        greenText = findViewById(R.id.textView2)
        blueText = findViewById(R.id.textView3)
        takeImageBtn.setOnClickListener(this)
        saveImageBtn.setOnClickListener(this)
        var customizeHandler: CustomizeHandler = CustomizeHandler()
        redSeek.setOnSeekBarChangeListener(customizeHandler)
        greenSeek.setOnSeekBarChangeListener(customizeHandler)
        blueSeek.setOnSeekBarChangeListener(customizeHandler)

    }

    override fun onClick(view: View?) {

        if (view != null)
            if (view.getId() == R.id.buttonTakeIamge) {
                if (Permission.getPermission(this)) {
                    var camerIntent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(camerIntent, 1000)
                } else
                    Toast.makeText(this, "No Camera", Toast.LENGTH_SHORT).show()
            } else if (view.getId() == R.id.buttonSaveTheGallery) {

                //if(Permission.getStoragePermission(this))
                Permission.getStoragePermission(this)
                try {

                        SaveImage.saveFile(this,bitmap)
                    Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                }
                catch( e:Exception)
                {
                    Toast.makeText(this,"Error = "+e.message,Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Toast.makeText(this, "Got result", Toast.LENGTH_SHORT).show()
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            var bundle = data?.getExtras()
            bitmap = bundle?.get("data") as Bitmap
            colourful = Colorful(bitmap, 0.0f, 0.0f, 0.0f)

            imageView?.setImageBitmap(bitmap)
        }
    }

    inner class CustomizeHandler : SeekBar.OnSeekBarChangeListener {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromBool: Boolean) {
            if (fromBool) {
                if (R.id.seekBar == seekbar?.getId()) {
                    colourful.setRedColor((progress / 100f) )
                    redText.setText((progress / 100f ).toString())
                }
                if (R.id.seekBar2 == seekbar?.getId()) {
                    colourful.setGreenColor((progress / 100f))
                    greenText.setText((progress / 100f ).toString())
                }
                if (R.id.seekBar3 == seekbar?.getId()) {
                    colourful.setBlueColor((progress / 100f) )
                    blueText.setText((progress / 100f ).toString())
                }
            }
            var bitmap = colourful.returnBitmap()
            imageView.setImageBitmap(bitmap)
        }

        override fun onStartTrackingTouch(p0: SeekBar?) {
            var a=1
        }

        override fun onStopTrackingTouch(p0: SeekBar?) {
            var b=1
        }


    }
}