package raf.rs.nutritiontracker.data.repositories

import io.reactivex.Observable
import raf.rs.nutritiontracker.data.datasource.MainDataSource
import raf.rs.nutritiontracker.model.entities.Ingredient

class IngredientsRepositoryImpl(private val mainDataSource: MainDataSource) :
    IngredientsRepository {
    override fun getAllIngredients(): Observable<List<Ingredient>> {
        return mainDataSource
            .getAllIngredients()
            .map {
                it.meals.map { ingredientResponse ->
                    Ingredient(
                        idIngredient = ingredientResponse.idIngredient!!,
                        strIngredient = ingredientResponse.strIngredient,
                        strDescription = ingredientResponse.strDescription,
                        strType = ingredientResponse.strType
                    )
                }
            }
    }
}