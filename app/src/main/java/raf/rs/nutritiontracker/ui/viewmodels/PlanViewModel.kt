package raf.rs.nutritiontracker.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import raf.rs.nutritiontracker.data.repositories.Plan
import raf.rs.nutritiontracker.model.entities.Meal
import raf.rs.nutritiontracker.ui.contracts.MainContract

class PlanViewModel : ViewModel(), MainContract.PlanViewModel {
    override var chosenPlan: MutableLiveData<Map<String, Map<String, Meal>>> = MutableLiveData()

    override var selectedMeal: MutableLiveData<Meal?> = MutableLiveData()
    override var selectedDay: MutableLiveData<String?> = MutableLiveData()
    override var selectedDayPeriod: MutableLiveData<String?> = MutableLiveData()

    override fun insertMealInDay(day: String, dayPeriod: String, meal: Meal) {
        Plan.chosenPlan.getOrPut(day) {
            mutableMapOf()
        }.getOrPut(dayPeriod) {
            meal
        }
        chosenPlan.value = Plan.chosenPlan
    }

    override fun getSelectedMeal(): Meal? {
        selectedMeal.value = Plan.selectedMeal
        return Plan.selectedMeal
    }

    override fun getSelectedDay(): String? {
        selectedDay.value = Plan.selectedDay
        return Plan.selectedDay
    }

    override fun getSelectedDayPeriod(): String? {
        selectedDayPeriod.value = Plan.selectedDayPeriod
        return Plan.selectedDayPeriod
    }

    override fun getChosenPlan(): LiveData<Map<String, Map<String, Meal>>> {
        chosenPlan.value = Plan.chosenPlan
        return chosenPlan
    }

    override fun setSelectedMeal(meal: Meal?) {
        selectedMeal.value = meal
        Plan.selectedMeal = meal
    }

    override fun setSelectedDay(day: String?) {
        selectedDay.value = day
        Plan.selectedDay = day
    }

    override fun setSelectedDayPeriod(day: String?) {
        selectedDayPeriod.value = day
        Plan.selectedDayPeriod = day
    }
}