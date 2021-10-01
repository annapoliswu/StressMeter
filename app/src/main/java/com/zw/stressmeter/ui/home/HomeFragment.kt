package com.zw.stressmeter.ui.home

import com.zw.stressmeter.ImageAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zw.stressmeter.R
import com.zw.stressmeter.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val grid1 = arrayOf(R.drawable.psm_anxious, R.drawable.psm_hiking3, R.drawable.psm_stressed_person3, R.drawable.psm_lonely2, R.drawable.psm_dog_sleeping, R.drawable.psm_running4, R.drawable.psm_alarm_clock, R.drawable.psm_headache, R.drawable.psm_baby_sleeping, R.drawable.psm_puppy, R.drawable.psm_stressed_cat,R.drawable.psm_angry_face,R.drawable.psm_bar,R.drawable.psm_running3,R.drawable.psm_neutral_child,R.drawable.psm_headache2)
    val grid2 = arrayOf(R.drawable.psm_mountains11,R.drawable.psm_wine3,R.drawable.psm_barbed_wire2,R.drawable.psm_clutter,R.drawable.psm_blue_drop,R.drawable.psm_to_do_list,R.drawable.psm_stressed_person7,R.drawable.psm_stressed_person6,R.drawable.psm_yoga4,R.drawable.psm_bird3,R.drawable.psm_stressed_person8,R.drawable.psm_exam4,R.drawable.psm_kettle,R.drawable.psm_lawn_chairs3,R.drawable.psm_to_do_list3,R.drawable.psm_work4)
    val grid3 = arrayOf(R.drawable.psm_talking_on_phone2,R.drawable.psm_stressed_person,R.drawable.psm_stressed_person12,R.drawable.psm_lonely,R.drawable.psm_gambling4,R.drawable.psm_clutter3,R.drawable.psm_reading_in_bed2,R.drawable.psm_stressed_person4,R.drawable.psm_lake3,R.drawable.psm_cat,R.drawable.psm_puppy3,R.drawable.psm_neutral_person2,R.drawable.psm_beach3,R.drawable.psm_peaceful_person,R.drawable.psm_alarm_clock2,R.drawable.psm_sticky_notes2)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //set-up
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val grid : GridView = binding.imageGridView
        val textView: TextView = binding.textHome
        val button : Button = binding.moreImagesButton

        val grid1Adapter = ImageAdapter(requireContext(), grid1)
        val grid2Adapter = ImageAdapter(requireContext(), grid2)
        val grid3Adapter = ImageAdapter(requireContext(), grid3)

        //observable items
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        button.setOnClickListener {
            homeViewModel.incrementGridNumber()
        }

        homeViewModel.gridNumber.observe(viewLifecycleOwner, Observer {
            println( "GridNum: " + it.toString())
            when(it){
                0 -> grid.adapter = grid1Adapter
                1 -> grid.adapter = grid2Adapter
                2 -> grid.adapter = grid3Adapter
            }
        })

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}