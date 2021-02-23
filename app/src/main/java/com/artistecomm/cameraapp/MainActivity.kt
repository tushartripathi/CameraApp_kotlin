package com.artistecomm.cameraapp

import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import com.artistecomm.SytemTask.Permission

class MainActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var takeImageBtn: Button
    private lateinit var saveImageBtn: Button
    private lateinit var imageView: ImageView
    private lateinit var redSeek: SeekBar
    private lateinit var greenSeek: SeekBar
    private lateinit var blueSeek: SeekBar
    private lateinit var redText: TextView
    private lateinit var greenText: TextView
    private lateinit var blueText: TextView




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

    }

    override fun onClick(view: View?) {

        if(view != null)
        if(view.getId()==R.id.buttonTakeIamge)
        {
           if(Permission.getPermission(this))
            {
                var camerIntent : Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(camerIntent, 1000)
            }
            else
                Toast.makeText(this,"No Camera",Toast.LENGTH_SHORT).show()
        }
        else if(view.getId()==R.id.buttonSaveTheGallery)
        {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Toast.makeText(this,"Got result",Toast.LENGTH_SHORT).show()
        if(requestCode==1000 && resultCode==RESULT_OK)
        {
            var bundle = data?.getExtras()
            var bitmap:Bitmap= bundle?.get("data") as Bitmap
            imageView?.setImageBitmap(bitmap)
        }
    }

    
}