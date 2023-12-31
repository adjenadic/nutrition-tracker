package raf.rs.nutritiontracker.ui.contracts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import raf.rs.nutritiontracker.model.entities.Area
import raf.rs.nutritiontracker.model.entities.Category
import raf.rs.nutritiontracker.model.entities.Ingredient
import raf.rs.nutritiontracker.model.entities.Meal
import raf.rs.nutritiontracker.model.entities.MealCount
import raf.rs.nutritiontracker.model.entities.MealDBEntity
import raf.rs.nutritiontracker.model.entities.MealDetails

interface MainContract {
    interface FilterViewModel {
        val ingredients: LiveData<List<Ingredient>>
        val ingredient: LiveData<Ingredient>

        val areas: LiveData<List<Area>>
        val area: LiveData<Area>

        fun getIngredients()
        fun getAreas()

        val categories: LiveData<List<Category>>
        val category: LiveData<Category>

        var filtered: MutableLiveData<List<Any>>

        fun getCategories()
        fun filterByCriteria(checked: Boolean, query: String, activePage: Int)
    }

    interface CategoryViewModel {
        val categories: LiveData<List<Category>>
        val category: LiveData<Category>

        fun getCategories()
    }

    interface MealViewModel {
        val meals: LiveData<List<Meal>>
        val meal: LiveData<Meal>

        fun getMeals(id: String)
        fun getMealsByArea(id: String)
        fun getMealsByIngredient(id: String)
        fun getMealsByName(id: String)
    }

    interface MealDetailsViewModel {
        val meal: LiveData<MealDetails>
        val meals: LiveData<List<MealDetails>>

        fun getMeal(id: String)
    }

    interface DBViewModel {
        val mealDB: LiveData<MealDBEntity>
        val mealsDB: LiveData<List<MealDBEntity>>
        val mealsSevenDaysDB: LiveData<List<MealCount>>

        fun insertMealInDB(mealDBEntity: MealDBEntity, callback: MealDBCallback)
        fun deleteAllMealsFromDB(callback: MealDBCallback)
        fun deleteMealByIDFromDB(id: Int, callback: MealDBCallback)
        fun getAllMealsFromDB()
        fun getMealByIDFromDB(id: Int)
        fun updateMealInDB(mealDBEntity: MealDBEntity, callback: MealDBCallback)

        fun getMealsForLastSevenDaysFromDB()
    }

    interface MealDBCallback {
        fun onMealSuccess()
        fun onMealError(error: Throwable)
    }

    interface PlanViewModel {
        var chosenPlan: MutableLiveData<Map<String, Map<String, Meal>>>

        var selectedMeal: MutableLiveData<Meal?>
        var selectedDay: MutableLiveData<String?>
        var selectedDayPeriod: MutableLiveData<String?>

        fun insertMealInDay(day: String, dayPeriod: String, meal: Meal)
        fun getSelectedMeal(): Meal?
        fun getSelectedDay(): String?
        fun getSelectedDayPeriod(): String?
        fun getChosenPlan(): LiveData<Map<String, Map<String, Meal>>>
        fun setSelectedMeal(meal: Meal?)
        fun setSelectedDay(day: String?)
        fun setSelectedDayPeriod(day: String?)
    }
}