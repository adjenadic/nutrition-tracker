package raf.rs.nutritiontracker.database

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface MealDBRepository {
    fun insertMeal(mealDBEntity: MealDBEntity): Completable
    fun deleteAllMeals(mealDBEntity: MealDBEntity): Completable
    fun deleteMealByID(mealDBEntity: MealDBEntity): Completable
    fun getAllMeals(): Observable<List<MealDBEntity>>
    fun getMealByID(id: Int): Single<MealDBEntity>
    fun updateMeal(mealDBEntity: MealDBEntity): Completable
}