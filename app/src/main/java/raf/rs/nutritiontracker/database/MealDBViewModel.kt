package raf.rs.nutritiontracker.database

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MealDBViewModel(private val mealDBRepository: MealDBRepository) : ViewModel(),
    MealDBContract.ViewModel {
    private val mealsDBD = CompositeDisposable()

    override fun insertMeal(mealDBEntity: MealDBEntity, callback: MealDBContract.MealCallback) {
        val mealDB = mealDBRepository
            .insertMeal(mealDBEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("COMPLETE INSERT")
                    callback.onMealInserted()
                },
                {
                    Timber.e(it)
                    callback.onInsertError(it)
                }
            )
        mealsDBD.add(mealDB)
    }

    override fun deleteAllMeals(mealDBEntity: MealDBEntity, callback: MealDBContract.MealCallback) {
        TODO("Not yet implemented")
    }

    override fun deleteMealByID(mealDBEntity: MealDBEntity, callback: MealDBContract.MealCallback) {
        TODO("Not yet implemented")
    }

    override fun getAllMeals() {
        TODO("Not yet implemented")
    }

    override fun getMealByID(id: Int) {
        TODO("Not yet implemented")
    }

    override fun updateMeal(mealDBEntity: MealDBEntity, callback: MealDBContract.MealCallback) {
        TODO("Not yet implemented")
    }

}