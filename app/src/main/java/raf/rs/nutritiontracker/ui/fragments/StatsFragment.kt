package raf.rs.nutritiontracker.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import org.koin.androidx.viewmodel.ext.android.viewModel
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.ui.contracts.MainContract
import raf.rs.nutritiontracker.ui.viewmodels.MealDBViewModel

class StatsFragment : Fragment(R.layout.stats_layout) {
    private val mealDBViewModel: MainContract.DBViewModel by viewModel<MealDBViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealDBViewModel.getMealsForLastSevenDaysFromDB()

        Handler(Looper.getMainLooper()).postDelayed({
            val statsChart = view.findViewById<PieChart>(R.id.statsChart)

            val entries = mutableListOf<PieEntry>()
            val list = mealDBViewModel.mealsSevenDaysDB.value!!
            for (i in 6 downTo 0) {
                try {
                    println(list[i].day)
                    print(list[i].mealCount)
                    entries.add(PieEntry(list[i].mealCount.toFloat(), list[i].day))
                } catch (e: IndexOutOfBoundsException) {
                    println(e)
                }
            }

            val dataSet = PieDataSet(entries, "")
            dataSet.valueTextSize = 16f
            dataSet.colors = listOf(
                Color.WHITE,
                Color.RED,
                Color.YELLOW,
                Color.GREEN,
                Color.CYAN,
                Color.BLUE,
                Color.MAGENTA
            )
            val data = PieData(dataSet)
            statsChart.apply {
                setUsePercentValues(false)
                description.isEnabled = false
                legend.isEnabled = true
                legend.textColor = Color.WHITE
                holeRadius = 1f
                transparentCircleRadius = 1f
                setEntryLabelColor(Color.BLACK)
                setEntryLabelTextSize(16f)
            }
            statsChart.data = data
            statsChart.invalidate()
        }, 150)
    }
}