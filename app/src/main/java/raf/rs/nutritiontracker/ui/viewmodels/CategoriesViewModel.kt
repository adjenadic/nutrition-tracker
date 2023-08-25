package raf.rs.nutritiontracker.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import raf.rs.nutritiontracker.data.repositories.CategoriesRepository
import raf.rs.nutritiontracker.model.entities.Category
import raf.rs.nutritiontracker.ui.contracts.MainContract
import timber.log.Timber

class CategoriesViewModel(private val categoriesRepository: CategoriesRepository) : ViewModel(),
    MainContract.CategoryViewModel {
    override val categories: MutableLiveData<List<Category>> = MutableLiveData()
    override val category: MutableLiveData<Category> = MutableLiveData()
    private val categoriesD = CompositeDisposable()

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
}