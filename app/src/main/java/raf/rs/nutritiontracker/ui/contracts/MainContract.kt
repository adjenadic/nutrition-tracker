package raf.rs.nutritiontracker.ui.contracts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import raf.rs.nutritiontracker.model.entities.Area
import raf.rs.nutritiontracker.model.entities.Category
import raf.rs.nutritiontracker.model.entities.Ingredient
import raf.rs.nutritiontracker.model.entities.Meal
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

    interface DetailedMealModel {
        val meal: LiveData<MealDetails>
        val meals: LiveData<List<MealDetails>>

        fun getMeal(id: String)
    }

//    interface SaveMealModel {
//        val meals: LiveData<List<MealSave>>
//        val savedMealsCount: LiveData<List<SavedMealsCount>>
//
//        fun getAllSavedMeals()
//        fun insertSavedMeal(mealSave: MealSave)
//        fun deleteAllStudents()
//        fun deleteMeal(id: Int)
//        fun updateMealById(id: Long, name: String, strYoutube: String, strInstructions: String)
//        fun getMealFromLast7Days()
//    }
}