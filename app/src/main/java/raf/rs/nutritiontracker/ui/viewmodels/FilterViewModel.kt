package raf.rs.nutritiontracker.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import raf.rs.nutritiontracker.data.repositories.AreasRepository
import raf.rs.nutritiontracker.data.repositories.CategoriesRepository
import raf.rs.nutritiontracker.data.repositories.IngredientsRepository
import raf.rs.nutritiontracker.model.entities.Area
import raf.rs.nutritiontracker.model.entities.Category
import raf.rs.nutritiontracker.model.entities.Ingredient
import raf.rs.nutritiontracker.ui.contracts.MainContract
import timber.log.Timber

class FilterViewModel(
    private val categoriesRepository: CategoriesRepository,
    private val areasRepository: AreasRepository,
    private val ingredientsRepository: IngredientsRepository
) : ViewModel(),
    MainContract.FilterViewModel {
    override val categories: MutableLiveData<List<Category>> = MutableLiveData()
    override val category: MutableLiveData<Category> = MutableLiveData()
    private val categoriesD = CompositeDisposable()

    override val areas: MutableLiveData<List<Area>> = MutableLiveData()
    override val area: MutableLiveData<Area> = MutableLiveData()
    private val areasD = CompositeDisposable()

    override val ingredients: MutableLiveData<List<Ingredient>> = MutableLiveData()
    override val ingredient: MutableLiveData<Ingredient> = MutableLiveData()
    private val ingredientsD = CompositeDisposable()

    override var filtered = MutableLiveData<List<Any>>()

    override fun getCategories() {
        val category = categoriesRepository
            .getAllCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    categories.value = it
                },
                {
                    Timber.e(it)
                },
                {
                    Timber.e("Completed")
                }
            )
        categoriesD.add(category)
    }

    override fun getIngredients() {
        val ingredient = ingredientsRepository
            .getAllIngredients()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    ingredients.value = it
                },
                {
                    Timber.e(it)
                },
                {
                    Timber.e("Completed")
                }
            )
        ingredientsD.add(ingredient)
    }

    override fun getAreas() {
        val area = areasRepository
            .getAllAreas()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    areas.value = it
                },
                {
                    Timber.e(it)
                },
                {
                    Timber.e("Completed")
                }
            )
        areasD.add(area)
    }

    override fun filterByCriteria(checked: Boolean, query: String, activePage: Int) {
        var filteredList = when (activePage) {
            1 -> {
                categories.value?.sortedBy { it.strCategory }
                categories.value?.filter { it.strCategory?.contains(query, true) == true }
            }

            2 -> {
                areas.value?.sortedBy { it.strArea }
                areas.value?.filter { it.strArea?.contains(query, true) == true }
            }

            3 -> {
                ingredients.value?.sortedBy { it.strIngredient }
                ingredients.value?.filter { it.strIngredient?.contains(query, true) == true }
            }

            else -> null
        }

        if (checked) {
            filteredList = filteredList?.asReversed()
        }

        filtered.value = filteredList!!
    }
}