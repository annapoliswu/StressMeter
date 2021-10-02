package com.zw.stressmeter.ui.results

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.marginRight
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zw.stressmeter.R
import com.zw.stressmeter.databinding.FragmentResultsBinding
import lecho.lib.hellocharts.*
import lecho.lib.hellocharts.gesture.ContainerScrollType
import lecho.lib.hellocharts.gesture.ZoomType
import lecho.lib.hellocharts.model.Axis
import lecho.lib.hellocharts.model.Line
import lecho.lib.hellocharts.model.PointValue
import lecho.lib.hellocharts.view.LineChartView

import lecho.lib.hellocharts.model.LineChartData
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

        var chart = binding.stressChart
        chart.isInteractive = true
        chart.zoomType = ZoomType.HORIZONTAL_AND_VERTICAL
        chart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL)

        val values: MutableList<PointValue> = ArrayList()
        values.add(PointValue(0F, 2F))
        values.add(PointValue(1F, 4F))
        values.add(PointValue(2F, 3F))
        values.add(PointValue(3F, 4F))
        val line = Line(values).setColor(Color.BLUE).setCubic(true)
        val lines: MutableList<Line> = ArrayList()
        lines.add(line)

        var yAxis = Axis()
        var xAxis = Axis()
        yAxis.setName("Stress Level")
        xAxis.setName("Instances")

        val data = LineChartData()
        data.lines = lines
        data.axisYLeft = yAxis
        data.axisXBottom = xAxis

        chart.lineChartData = data


        val table = binding.stressTable
        val file = File(requireContext().getExternalFilesDir(null), fileName)
        if (file.exists() && !file.isDirectory) {
            file.forEachLine {
                val strings = it.split(",").toTypedArray()
                addTableRow(table, strings)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun addTableRow(table:TableLayout, strings:Array<String>){
        var tableRow =  TableRow(context)
        var c1 = TextView(context)
        var c2 = TextView(context)
        c1.text = strings[0]
        c2.text = strings[1]
        c1.setPadding(50,20,50,20)
        c2.setPadding(50,20,50,20)
        tableRow.addView(c1)
        tableRow.addView(c2)

        var hLine = View(context)
        hLine.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1)
        hLine.setBackgroundColor(Color.parseColor("#000000"))

        table.addView(hLine)
        table.addView(tableRow)
    }
}