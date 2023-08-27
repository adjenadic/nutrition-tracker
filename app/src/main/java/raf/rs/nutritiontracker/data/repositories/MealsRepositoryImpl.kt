package raf.rs.nutritiontracker.data.repositories

import io.reactivex.Observable
import raf.rs.nutritiontracker.data.source.MainDataSource
import raf.rs.nutritiontracker.model.entities.Meal

class MealsRepositoryImpl(private val mainDataSource: MainDataSource) : MealsRepository {
    override fun getAllMeals(id: String): Observable<List<Meal>> {
        return mainDataSource
            .getMealsByCategory(id)
            .map {
                it.meals?.map { mealResponse ->
                    Meal(
                        idMeal = mealResponse.idMeal!!,
                        strMeal = mealResponse.strMeal!!,
                        strMealThumb = mealResponse.strMealThumb,
                    )
                } ?: emptyList()
            }
    }

    override fun getAllMealsByArea(id: String): Observable<List<Meal>> {
        return mainDataSource
            .getMealsByArea(id)
            .map {
                it.meals?.map { mealResponse ->
                    Meal(
                        idMeal = mealResponse.idMeal!!,
                        strMeal = mealResponse.strMeal!!,
                        strMealThumb = mealResponse.strMealThumb
                    )
                } ?: emptyList()
            }
    }

    override fun getAllMealsByIngredient(id: String): Observable<List<Meal>> {
        return mainDataSource
            .getMealsByIngredient(id)
            .map {
                it.meals?.map { mealResponse ->
                    Meal(
                        idMeal = mealResponse.idMeal!!,
                        strMeal = mealResponse.strMeal!!,
                        strMealThumb = mealResponse.strMealThumb
                    )
                } ?: emptyList()
            }
    }

    override fun getAllMealsByName(id: String): Observable<List<Meal>> {
        return mainDataSource
            .getFilterByName(id)
            .map {
                it.meals?.map { mealResponse ->
                    Meal(
                        idMeal = mealResponse.idMeal!!,
                        strMeal = mealResponse.strMeal!!,
                        strMealThumb = mealResponse.strMealThumb
                    )
                } ?: emptyList()
            }
    }
}