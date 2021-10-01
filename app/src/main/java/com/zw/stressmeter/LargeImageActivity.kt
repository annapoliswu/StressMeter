package com.zw.stressmeter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class LargeImageActivity : AppCompatActivity() {
    var gridNum : Int = -1
    var position : Int = -1
    lateinit var imageView : ImageView
    lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_large_image)
        gridNum = intent.getIntExtra("gridNum", -1)
        position = intent.getIntExtra("position", -1)

        imageView = findViewById(R.id.large_image)
        imageAdapter = ImageAdapter(this)
        imageAdapter.changeGrid(gridNum)
        imageView.setImageResource(imageAdapter.images[position])
    }

    fun onCancelClicked(view : View){
        finish()
    }

    fun onSubmitClicked(view: View){
        //TODO: write entry (time,position/stressnum) to csv
        println("Intent: gridnum:$gridNum , position:$position")
        finish()

    }

}