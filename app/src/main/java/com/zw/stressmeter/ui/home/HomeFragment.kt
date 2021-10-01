package com.zw.stressmeter.ui.home

import android.content.Context
import com.zw.stressmeter.ImageAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import android.content.Intent

import android.widget.AdapterView.OnItemClickListener
import com.zw.stressmeter.LargeImageActivity
import com.zw.stressmeter.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


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


        //observable items
        homeViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })

        button.setOnClickListener {
            homeViewModel.incrementGridNumber()
        }

        val gridAdapter = ImageAdapter(requireContext())
        homeViewModel.gridNumber.observe(viewLifecycleOwner, {
            println("GridNum: $it")
            gridAdapter.changeGrid(it)
            grid.adapter = gridAdapter
        })

        grid.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(
                context,
                LargeImageActivity::class.java
            )

            intent.putExtra("gridNum", homeViewModel.gridNumber.value)
            intent.putExtra("position", position)
            startActivity(intent)
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}