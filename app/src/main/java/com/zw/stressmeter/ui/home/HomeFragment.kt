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

    val grid1 = arrayOf(R.drawable.fish_normal017, R.drawable.psm_alarm_clock,R.drawable.psm_alarm_clock,R.drawable.psm_alarm_clock,R.drawable.psm_alarm_clock,R.drawable.psm_alarm_clock,R.drawable.psm_alarm_clock,R.drawable.psm_alarm_clock,R.drawable.psm_alarm_clock,R.drawable.psm_alarm_clock,R.drawable.psm_alarm_clock,R.drawable.psm_alarm_clock,R.drawable.psm_alarm_clock,R.drawable.psm_alarm_clock,R.drawable.psm_alarm_clock,R.drawable.psm_alarm_clock)
    val grid2 = arrayOf(R.drawable.fish_normal017)
    val grid3 = arrayOf(R.drawable.fish_normal017)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val button : Button = binding.moreImagesButton
        button.setOnClickListener {
            homeViewModel.incrementGridNumber()
        }
        homeViewModel.gridNumber.observe(viewLifecycleOwner, Observer {
            println( "GridNum: " + it.toString())
        })

        val grid : GridView = binding.imageGridView
        grid.adapter = ImageAdapter(requireContext(), grid1 )


        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}