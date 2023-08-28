package raf.rs.nutritiontracker.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.model.entities.Meal
import raf.rs.nutritiontracker.ui.contracts.MainContract
import raf.rs.nutritiontracker.ui.viewmodels.PlanViewModel

class DailyPlanFragment(var day: String) : Fragment(R.layout.daily_plan_layout) {
    private val planViewModel: MainContract.PlanViewModel by viewModel<PlanViewModel>()
    var selectedMeal: Meal? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chooseDay: TextView = view.findViewById(R.id.chooseDay)
        val btnBreakfast: Button = view.findViewById(R.id.btnBreakfast)
        val btnLunch: Button = view.findViewById(R.id.btnLunch)
        val btnSnack: Button = view.findViewById(R.id.btnSnack)
        val btnDinner: Button = view.findViewById(R.id.btnDinner)
        val chooseBreakfast: TextView = view.findViewById(R.id.chooseBreakfast)
        val chooseLunch: TextView = view.findViewById(R.id.chooseLunch)
        val chooseSnack: TextView = view.findViewById(R.id.chooseSnack)
        val chooseDinner: TextView = view.findViewById(R.id.chooseDinner)
        val btnSaveDPlan: Button = view.findViewById(R.id.btnSaveDPlan)

        val chosenPlan = planViewModel.getChosenPlan().value
        btnBreakfast.setOnClickListener {
            fragmentTransaction("Breakfast")
        }
        btnLunch.setOnClickListener {
            fragmentTransaction("Lunch")
        }
        btnSnack.setOnClickListener {
            fragmentTransaction("Snack")
        }
        btnDinner.setOnClickListener {
            fragmentTransaction("Dinner")
        }

        planViewModel.chosenPlan.observe(viewLifecycleOwner) {
            chooseDay.text = day

            println(chosenPlan?.get(day)?.get("Breakfast")?.strMeal)
            if (!chosenPlan?.get(day)?.get("Breakfast")?.strMeal.isNullOrBlank()) {
                chooseBreakfast.text = chosenPlan?.get(day)?.get("Breakfast")?.strMeal
            }
            if (!chosenPlan?.get(day)?.get("Lunch")?.strMeal.isNullOrBlank()) {
                chooseLunch.text = chosenPlan?.get(day)?.get("Lunch")?.strMeal
            }
            if (!chosenPlan?.get(day)?.get("Snack")?.strMeal.isNullOrBlank()) {
                chooseSnack.text = chosenPlan?.get(day)?.get("Snack")?.strMeal
            }
            if (!chosenPlan?.get(day)?.get("Dinner")?.strMeal.isNullOrBlank()) {
                chooseDinner.text = chosenPlan?.get(day)?.get("Dinner")?.strMeal
            }

            println(chooseBreakfast.text)
        }

        val selected = planViewModel.getSelectedMeal()
        val dayPeriod = planViewModel.getSelectedDayPeriod()
        if (selected != null && !dayPeriod.isNullOrBlank()) {
            when (planViewModel.getSelectedDayPeriod()) {
                "Breakfast" -> {
                    chooseBreakfast.text = selected.strMeal
                    planViewModel.insertMealInDay(day, dayPeriod, selected)
                    planViewModel.setSelectedMeal(null)
                }

                "Lunch" -> {
                    chooseLunch.text = selected.strMeal
                    planViewModel.insertMealInDay(day, dayPeriod, selected)
                    planViewModel.setSelectedMeal(null)
                }

                "Snack" -> {
                    chooseSnack.text = selected.strMeal
                    planViewModel.insertMealInDay(day, dayPeriod, selected)
                    planViewModel.setSelectedMeal(null)
                }

                "Dinner" -> {
                    chooseDinner.text = selected.strMeal
                    planViewModel.insertMealInDay(day, dayPeriod, selected)
                    planViewModel.setSelectedMeal(null)
                }
            }
        }

        btnSaveDPlan.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun fragmentTransaction(dayPeriod: String) {
        planViewModel.setSelectedDayPeriod(dayPeriod)
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainFL, CategoriesFragment(), "DailyPlanFragment")
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("DailyPlanFragment")
        fragmentTransaction.commit()
    }
}