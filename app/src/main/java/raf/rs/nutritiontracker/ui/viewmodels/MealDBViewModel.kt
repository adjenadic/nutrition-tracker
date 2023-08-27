package raf.rs.nutritiontracker.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import raf.rs.nutritiontracker.data.repositories.MealDBRepository
import raf.rs.nutritiontracker.model.entities.MealDBEntity
import raf.rs.nutritiontracker.ui.contracts.MainContract
import timber.log.Timber

class MealDBViewModel(private val mealDBRepository: MealDBRepository) : ViewModel(),
    MainContract.DBViewModel {
    override val mealsDB: MutableLiveData<List<MealDBEntity>> = MutableLiveData()
    override val mealDB: MutableLiveData<MealDBEntity> = MutableLiveData()
    private val mealsDBD = CompositeDisposable()

    override fun insertMealInDB(mealDBEntity: MealDBEntity, callback: MainContract.MealDBCallback) {
        val mealDB = mealDBRepository
            .insertMeal(mealDBEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Insertion completed.")
                    callback.onMealSuccess()
                },
                {
                    Timber.e(it)
                    callback.onMealError(it)
                }
            )
        mealsDBD.add(mealDB)
    }

    override fun deleteAllMealsFromDB(callback: MainContract.MealDBCallback) {
        val mealDB = mealDBRepository
            .deleteAllMeals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Deletion completed.")
                    callback.onMealSuccess()
                },
                {
                    Timber.e(it)
                    callback.onMealError(it)
                }
            )
        mealsDBD.add(mealDB)
    }

    override fun deleteMealByIDFromDB(id: Int, callback: MainContract.MealDBCallback) {
        val mealDB = mealDBRepository
            .deleteMealByID(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Deletion completed.")
                    callback.onMealSuccess()
                },
                {
                    Timber.e(it)
                    callback.onMealError(it)
                }
            )
        mealsDBD.add(mealDB)
    }

    override fun getAllMealsFromDB() {
        val mealDB = mealDBRepository
            .getAllMeals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mealsDB.value = it
                    Timber.e("Fetching completed.")
                },
                {
                    Timber.e(it)
                }
            )
        mealsDBD.add(mealDB)
    }

    override fun getMealByIDFromDB(id: Int) {
        val mealDB = mealDBRepository
            .getMealByID(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mealDB.value = it
                    Timber.e("Fetching completed.")
                },
                {
                    Timber.e(it)
                }
            )
        mealsDBD.add(mealDB)
    }

    override fun updateMealInDB(mealDBEntity: MealDBEntity, callback: MainContract.MealDBCallback) {
        val mealDB = mealDBRepository
            .updateMeal(mealDBEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Update completed.")
                    callback.onMealSuccess()
                },
                {
                    Timber.e(it)
                    callback.onMealError(it)
                }
            )
        mealsDBD.add(mealDB)
    }
}