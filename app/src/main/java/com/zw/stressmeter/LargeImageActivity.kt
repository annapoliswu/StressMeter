package com.zw.stressmeter

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.sql.Timestamp

/*
* Activity to show enlarged image and confirm to write data to csv
 */
class LargeImageActivity : AppCompatActivity() {
    var gridNum : Int = -1
    var position : Int = -1
    lateinit var imageView : ImageView
    lateinit var imageAdapter: ImageAdapter

    val FILE_HEADER = "Time, Stress"
    val fileName = "stressReadings.csv"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_large_image)

        //Get grid and position of image previously clicked
        gridNum = intent.getIntExtra("gridNum", -1)
        position = intent.getIntExtra("position", -1)

        //Set the enlarged image
        imageView = findViewById(R.id.large_image)
        imageAdapter = ImageAdapter(this)
        imageAdapter.changeGrid(gridNum)
        imageView.setImageResource(imageAdapter.images[position])
    }

    fun onCancelClicked(view : View){
        finish()
    }

    fun onSubmitClicked(view: View){
        appendToCsv(position)
        finish()
    }

    /*
     * Creates csv file if none. Writes line to csv using system time and stress value input.
     */
    fun appendToCsv(stressValue : Int){
        val timestamp = Timestamp(System.currentTimeMillis()).time

        val file = File(getExternalFilesDir(null), fileName)
        if (!file.exists() || file.isDirectory) {
            file.createNewFile()
            file.appendText(FILE_HEADER)
        }
        val line = "\n$timestamp, $stressValue"
        file.appendText(line)
        //Log.d("append" ,line)
    }
}