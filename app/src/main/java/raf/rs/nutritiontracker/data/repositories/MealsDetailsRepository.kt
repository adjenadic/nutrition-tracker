package raf.rs.nutritiontracker.data.repositories

import io.reactivex.Observable
import raf.rs.nutritiontracker.model.entities.MealDetails

interface MealsDetailsRepository {
    fun getMeal(id: String): Observable<List<MealDetails>>
}