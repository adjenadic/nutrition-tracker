package raf.rs.nutritiontracker.data.repositories

import io.reactivex.Observable
import raf.rs.nutritiontracker.data.datasource.MainDataSource
import raf.rs.nutritiontracker.model.entities.Category

class CategoriesRepositoryImpl(private val mainDataSource: MainDataSource) : CategoriesRepository {
    override fun getAllCategories(): Observable<List<Category>> {
        return mainDataSource
            .getAllCategories()
            .map {
                it.categories.map { categoryResponse ->
                    Category(
                        idCategory = categoryResponse.idCategory!!,
                        strCategory = categoryResponse.strCategory!!,
                        strCategoryThumb = categoryResponse.strCategoryThumb!!,
                        strCategoryDescription = categoryResponse.strCategoryDescription!!
                    )
                }
            }
    }
}