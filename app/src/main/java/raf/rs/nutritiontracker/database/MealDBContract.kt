package raf.rs.nutritiontracker.database

interface MealDBContract {
    interface ViewModel {
        fun insertMeal(mealDBEntity: MealDBEntity, callback: MealCallback)
        fun deleteAllMeals(mealDBEntity: MealDBEntity, callback: MealCallback)
        fun deleteMealByID(mealDBEntity: MealDBEntity, callback: MealCallback)
        fun getAllMeals()
        fun getMealByID(id: Int)
        fun updateMeal(mealDBEntity: MealDBEntity, callback: MealCallback)
    }

    interface MealCallback {
        fun onMealInserted()
        fun onInsertError(error: Throwable)
    }
}