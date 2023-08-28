package raf.rs.nutritiontracker.data.repositories

import raf.rs.nutritiontracker.model.entities.Meal

object Plan {
    var chosenPlan: MutableMap<String, MutableMap<String, Meal>> = mutableMapOf()

    var selectedMeal: Meal? = null
    var selectedDay: String? = null
    var selectedDayPeriod: String? = null
}