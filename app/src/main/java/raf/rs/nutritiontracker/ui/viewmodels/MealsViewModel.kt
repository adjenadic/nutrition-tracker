package raf.rs.nutritiontracker.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import raf.rs.nutritiontracker.data.repositories.MealsRepository
import raf.rs.nutritiontracker.model.entities.Meal
import raf.rs.nutritiontracker.ui.contracts.MainContract
import timber.log.Timber

class MealsViewModel(private val mealsRepository: MealsRepository) : ViewModel(),
    MainContract.MealViewModel {
    override val meals: MutableLiveData<List<Meal>> = MutableLiveData()
    override val meal: MutableLiveData<Meal> = MutableLiveData()
    private val mealsD = CompositeDisposable()

    override fun getMeals(id: String) {
        val disposable = mealsRepository.getAllMeals(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { fetchedMeals ->
                    meals.value = fetchedMeals
                },
                { error ->
                    Timber.e(error)
                }
            )
        mealsD.add(disposable)
    }

    override fun getMealsByArea(id: String) {
        val searchDisposable = mealsRepository.getAllMealsByArea(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { searchedMeals ->
                    meals.value = searchedMeals
                },
                { error ->
                    Timber.e(error)
                }
            )
        mealsD.add(searchDisposable)
    }

    override fun getMealsByIngredient(id: String) {
        val searchDisposable = mealsRepository.getAllMealsByIngredient(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { searchedMeals ->
                    meals.value = searchedMeals
                },
                { error ->
                    Timber.e(error)
                }
            )
        mealsD.add(searchDisposable)
    }

    override fun getMealsByName(id: String) {
        val searchDisposable = mealsRepository.getAllMealsByName(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { searchedMeals ->
                    meals.value = searchedMeals
                },
                { error ->
                    Timber.e(error)
                }
            )
        mealsD.add(searchDisposable)
    }
}