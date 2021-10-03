package com.zw.stressmeter.ui.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Touch the image that best captures how stressed you feel right now"
    }
    val text: LiveData<String> = _text

    //So gridNumber holds across orientation change
    var gridNumber = MutableLiveData<Int>().apply{
        value = 0
    }

    // Cycles between the 3 grid numbers
    public fun incrementGridNumber(){
        gridNumber.value = gridNumber.value?.plus(1)?.mod(3)
    }



}