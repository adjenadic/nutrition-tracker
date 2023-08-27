package raf.rs.nutritiontracker.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import raf.rs.nutritiontracker.model.entities.MealDBEntity

@Dao
abstract class MealDBDao {
    @Insert
    abstract fun insertMeal(mealDBEntity: MealDBEntity): Completable

    @Query("DELETE FROM meals")
    abstract fun deleteAllMeals(): Completable

    @Query("DELETE FROM meals where idMeal=:id")
    abstract fun deleteMealByID(id: Int): Completable

    @Query("Select * from meals")
    abstract fun getAllMeals(): Observable<List<MealDBEntity>>

    @Query("Select * FROM meals where idMeal=:id")
    abstract fun getMealByID(id: Int): Single<MealDBEntity>

    @Update
    abstract fun updateMeal(mealDBEntity: MealDBEntity): Completable
}