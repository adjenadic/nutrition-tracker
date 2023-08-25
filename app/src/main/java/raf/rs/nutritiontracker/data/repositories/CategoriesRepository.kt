package raf.rs.nutritiontracker.data.repositories

import io.reactivex.Observable
import raf.rs.nutritiontracker.model.entities.Category

interface CategoriesRepository {
    fun getAllCategories(): Observable<List<Category>>
}