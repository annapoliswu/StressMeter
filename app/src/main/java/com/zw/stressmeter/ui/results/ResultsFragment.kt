package com.zw.stressmeter.ui.results

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zw.stressmeter.databinding.FragmentResultsBinding
import lecho.lib.hellocharts.gesture.ContainerScrollType
import lecho.lib.hellocharts.gesture.ZoomType
import lecho.lib.hellocharts.model.*

import java.io.File


class ResultsFragment : Fragment() {

    private val fileName = "stressReadings.csv"
    private lateinit var resultsViewModel: ResultsViewModel
    private var _binding: FragmentResultsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resultsViewModel =
            ViewModelProvider(this).get(ResultsViewModel::class.java)

        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textResults
        resultsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        //setting chart interactables
        val chart = binding.stressChart
        chart.isInteractive = true
        chart.zoomType = ZoomType.HORIZONTAL_AND_VERTICAL
        chart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL)

        //chart axes
        val data = LineChartData()
        val yAxis = Axis()
        val xAxis = Axis()
        yAxis.setName("Stress Level")
        xAxis.setName("Instances")
        data.axisYLeft = yAxis
        data.axisXBottom = xAxis

        //populating the chart data from csv file
        val values: MutableList<PointValue> = ArrayList()
        val table = binding.stressTable
        val file = File(requireContext().getExternalFilesDir(null), fileName)
        if (file.exists() && !file.isDirectory) {
            var index = -1
            file.forEachLine {
                val strings = it.split(",").toTypedArray()
                addTableRow(table, strings)

                if(index > -1) {
                    values.add( PointValue(index.toFloat(), strings[1].toFloat()) )
                }
                index++
            }
        }

        //line creation and setting chart line
        val lines: MutableList<Line> = ArrayList()
        val line = Line(values).setColor(Color.BLUE).setCubic(true)
        lines.add(line)
        data.lines = lines
        chart.lineChartData = data

        //chart viewport adjustment so y-axis is not cut off
        val vp = Viewport(chart.maximumViewport);
        vp.top = vp.top + 4
        chart.maximumViewport = vp
        chart.currentViewport = vp

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*
        Formats row, adds a row and line separator to table
     */
    fun addTableRow(table:TableLayout, strings:Array<String>){
        val tableRow =  TableRow(context)
        val c1 = TextView(context)
        val c2 = TextView(context)
        c1.text = strings[0]
        c2.text = strings[1]
        c1.setPadding(50,20,50,20)
        c2.setPadding(50,20,50,20)
        tableRow.addView(c1)
        tableRow.addView(c2)

        val hLine = View(context)
        hLine.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1)
        hLine.setBackgroundColor(Color.parseColor("#000000"))

        table.addView(hLine)
        table.addView(tableRow)
    }
}