package com.zw.stressmeter

import android.R
import android.view.ViewGroup


import android.view.View
import android.content.Context
import android.icu.number.IntegerWidth
import android.view.LayoutInflater
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import android.util.DisplayMetrics





class ImageAdapter(applicationContext: Context, images: Array<Int>) : BaseAdapter() {
    private lateinit var context: Context
    public lateinit var images: Array<Int>
    private var screenWidth : Int

    init {
        this.context = applicationContext
        this.images = images

        val metrics: DisplayMetrics = context.getResources().getDisplayMetrics()
        screenWidth = metrics.widthPixels
    }


    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(position: Int): Any {
        return images[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView = ImageView(context)
        imageView.setImageResource(images[position])
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
        var imageDim = (screenWidth)/4
        imageView.layoutParams = AbsListView.LayoutParams(imageDim,imageDim)

        return imageView
    }


}