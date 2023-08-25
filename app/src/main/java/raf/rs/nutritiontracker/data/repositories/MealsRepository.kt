package raf.rs.nutritiontracker.data.repositories

import io.reactivex.Observable
import raf.rs.nutritiontracker.model.entities.Meal

interface MealsRepository {
    fun getAllMeals(id: String): Observable<List<Meal>>

    fun getAllMealsByArea(id: String): Observable<List<Meal>>

    fun getAllMealsByIngredient(id: String): Observable<List<Meal>>

    fun getAllMealsByName(id: String): Observable<List<Meal>>
}