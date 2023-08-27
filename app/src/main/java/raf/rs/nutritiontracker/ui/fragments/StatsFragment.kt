package raf.rs.nutritiontracker.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.ui.contracts.MainContract
import raf.rs.nutritiontracker.ui.viewmodels.MealDBViewModel

class StatsFragment : Fragment(R.layout.stats_layout) {
    private val mealDBViewModel: MainContract.DBViewModel by viewModel<MealDBViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}