package raf.rs.nutritiontracker.data.repositories

import io.reactivex.Observable
import raf.rs.nutritiontracker.model.entities.Ingredient

interface IngredientsRepository {
    fun getAllIngredients(): Observable<List<Ingredient>>
}