package raf.rs.nutritiontracker.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import raf.rs.nutritiontracker.data.repositories.MealsDetailsRepository
import raf.rs.nutritiontracker.model.entities.MealDetails
import raf.rs.nutritiontracker.ui.contracts.MainContract
import timber.log.Timber

class MealDetailsViewModel(private val mealsDetailsRepository: MealsDetailsRepository) :
    ViewModel(),
    MainContract.DetailedMealModel {
    private val mealsD = CompositeDisposable()
    override val meals: MutableLiveData<List<MealDetails>> = MutableLiveData()
    override val meal: MutableLiveData<MealDetails> = MutableLiveData()

    override fun getMeal(id: String) {
        val meals = mealsDetailsRepository
            .getMeal(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    meals.value = it;
                },
                {
                    Timber.e(it)
                },
                {
                    Timber.e("Completed")
                }
            )
        mealsD.add(meals)
    }
}