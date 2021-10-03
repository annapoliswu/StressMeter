package com.zw.stressmeter
import android.view.ViewGroup
import android.view.View
import android.content.Context
import android.widget.*
import android.util.DisplayMetrics


/**
 * Handles display for images in grid
 */
class ImageAdapter(applicationContext: Context) : BaseAdapter() {
    private var context: Context
    var images: Array<Int>
    private var screenWidth : Int

    private val grid1 = arrayOf(
        R.drawable.psm_anxious,
        R.drawable.psm_hiking3,
        R.drawable.psm_stressed_person3,
        R.drawable.psm_lonely2,
        R.drawable.psm_dog_sleeping,
        R.drawable.psm_running4,
        R.drawable.psm_alarm_clock,
        R.drawable.psm_headache,
        R.drawable.psm_baby_sleeping,
        R.drawable.psm_puppy,
        R.drawable.psm_stressed_cat,
        R.drawable.psm_angry_face,
        R.drawable.psm_bar,
        R.drawable.psm_running3,
        R.drawable.psm_neutral_child,
        R.drawable.psm_headache2)

    private val grid2 = arrayOf(
        R.drawable.psm_mountains11,
        R.drawable.psm_wine3,
        R.drawable.psm_barbed_wire2,
        R.drawable.psm_clutter,
        R.drawable.psm_blue_drop,
        R.drawable.psm_to_do_list,
        R.drawable.psm_stressed_person7,
        R.drawable.psm_stressed_person6,
        R.drawable.psm_yoga4,
        R.drawable.psm_bird3,
        R.drawable.psm_stressed_person8,
        R.drawable.psm_exam4,
        R.drawable.psm_kettle,
        R.drawable.psm_lawn_chairs3,
        R.drawable.psm_to_do_list3,
        R.drawable.psm_work4)

    private val grid3 = arrayOf(
        R.drawable.psm_talking_on_phone2,
        R.drawable.psm_stressed_person,
        R.drawable.psm_stressed_person12,
        R.drawable.psm_lonely,
        R.drawable.psm_gambling4,
        R.drawable.psm_clutter3,
        R.drawable.psm_reading_in_bed2,
        R.drawable.psm_stressed_person4,
        R.drawable.psm_lake3,
        R.drawable.psm_cat,
        R.drawable.psm_puppy3,
        R.drawable.psm_neutral_person2,
        R.drawable.psm_beach3,
        R.drawable.psm_peaceful_person,
        R.drawable.psm_alarm_clock2,
        R.drawable.psm_sticky_notes2)

    init {
        this.context = applicationContext
        this.images = grid1
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

        //sets imageView to be a square
        val imageDim = (screenWidth)/4
        imageView.layoutParams = AbsListView.LayoutParams(imageDim,imageDim)

        return imageView
    }

    /**
     * Changes which predefined array of images will be displayed in grid
     */
    fun changeGrid(gridNum : Int){
        when(gridNum){
            0 -> images = grid1
            1 -> images = grid2
            2 -> images = grid3
        }
    }


}