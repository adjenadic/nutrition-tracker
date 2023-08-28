package raf.rs.nutritiontracker.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import raf.rs.nutritiontracker.model.entities.MealCount
import raf.rs.nutritiontracker.model.entities.MealDBEntity

interface MealDBRepository {
    fun insertMeal(mealDBEntity: MealDBEntity): Completable
    fun deleteAllMeals(): Completable
    fun deleteMealByID(id: Int): Completable
    fun getAllMeals(): Observable<List<MealDBEntity>>
    fun getMealByID(id: Int): Single<MealDBEntity>
    fun updateMeal(mealDBEntity: MealDBEntity): Completable
    fun getMealsForLastSevenDaysFromDB(): Observable<List<MealCount>>
}