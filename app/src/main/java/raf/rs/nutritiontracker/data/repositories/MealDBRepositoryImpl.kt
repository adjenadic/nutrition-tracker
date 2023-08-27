package raf.rs.nutritiontracker.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import raf.rs.nutritiontracker.database.MealDBDao
import raf.rs.nutritiontracker.model.entities.MealDBEntity

class MealDBRepositoryImpl(private val mealDBDao: MealDBDao) : MealDBRepository {
    override fun insertMeal(mealDBEntity: MealDBEntity): Completable {
        return mealDBDao.insertMeal(mealDBEntity)
    }

    override fun deleteAllMeals(): Completable {
        return mealDBDao.deleteAllMeals()
    }

    override fun deleteMealByID(id: Int): Completable {
        return mealDBDao.deleteMealByID(id)
    }

    override fun getAllMeals(): Observable<List<MealDBEntity>> {
        return mealDBDao.getAllMeals()
    }

    override fun getMealByID(id: Int): Single<MealDBEntity> {
        return mealDBDao.getMealByID(id)
    }

    override fun updateMeal(mealDBEntity: MealDBEntity): Completable {
        return mealDBDao.updateMeal(mealDBEntity)
    }
}