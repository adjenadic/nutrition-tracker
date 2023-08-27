package raf.rs.nutritiontracker.database

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class MealDBRepositoryImpl (private val mealDBDao: MealDBDao): MealDBRepository {
    override fun insertMeal(mealDBEntity: MealDBEntity): Completable {
        return mealDBDao.insertMeal(mealDBEntity)
    }

    override fun deleteAllMeals(mealDBEntity: MealDBEntity): Completable {
        TODO("Not yet implemented")
    }

    override fun deleteMealByID(mealDBEntity: MealDBEntity): Completable {
        TODO("Not yet implemented")
    }

    override fun getAllMeals(): Observable<List<MealDBEntity>> {
        TODO("Not yet implemented")
    }

    override fun getMealByID(id: Int): Single<MealDBEntity> {
        TODO("Not yet implemented")
    }

    override fun updateMeal(mealDBEntity: MealDBEntity): Completable {
        TODO("Not yet implemented")
    }
}