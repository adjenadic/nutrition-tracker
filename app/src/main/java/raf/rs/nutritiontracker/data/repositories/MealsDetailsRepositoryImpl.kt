package raf.rs.nutritiontracker.data.repositories

import io.reactivex.Observable
import raf.rs.nutritiontracker.data.source.MainDataSource
import raf.rs.nutritiontracker.model.entities.MealDetails

class MealsDetailsRepositoryImpl(private val mainDataSource: MainDataSource) :
    MealsDetailsRepository {
    override fun getMeal(id: String): Observable<List<MealDetails>> {
        return mainDataSource
            .getMealDetails(id)
            .map {
                it.meals.map { mealDetailsResponse ->
                    MealDetails(
                        idMeal = mealDetailsResponse.idMeal,
                        strMeal = mealDetailsResponse.strMeal,
                        strMealThumb = mealDetailsResponse.strMealThumb,
                        strCategory = mealDetailsResponse.strCategory,
                        strArea = mealDetailsResponse.strArea,
                        strInstructions = mealDetailsResponse.strInstructions,
                        strTags = mealDetailsResponse.strTags,
                        strYoutube = mealDetailsResponse.strYoutube,
                        strIngredient1 = mealDetailsResponse.strIngredient1,
                        strIngredient2 = mealDetailsResponse.strIngredient2,
                        strIngredient3 = mealDetailsResponse.strIngredient3,
                        strIngredient4 = mealDetailsResponse.strIngredient4,
                        strIngredient5 = mealDetailsResponse.strIngredient5,
                        strIngredient6 = mealDetailsResponse.strIngredient6,
                        strIngredient7 = mealDetailsResponse.strIngredient7,
                        strIngredient8 = mealDetailsResponse.strIngredient8,
                        strIngredient9 = mealDetailsResponse.strIngredient9,
                        strIngredient10 = mealDetailsResponse.strIngredient10,
                        strIngredient11 = mealDetailsResponse.strIngredient11,
                        strIngredient12 = mealDetailsResponse.strIngredient12,
                        strIngredient13 = mealDetailsResponse.strIngredient13,
                        strIngredient14 = mealDetailsResponse.strIngredient14,
                        strIngredient15 = mealDetailsResponse.strIngredient15,
                        strIngredient16 = mealDetailsResponse.strIngredient16,
                        strIngredient17 = mealDetailsResponse.strIngredient17,
                        strIngredient18 = mealDetailsResponse.strIngredient18,
                        strIngredient19 = mealDetailsResponse.strIngredient19,
                        strIngredient20 = mealDetailsResponse.strIngredient20,
                        strMeasure1 = mealDetailsResponse.strMeasure1,
                        strMeasure2 = mealDetailsResponse.strMeasure2,
                        strMeasure3 = mealDetailsResponse.strMeasure3,
                        strMeasure4 = mealDetailsResponse.strMeasure4,
                        strMeasure5 = mealDetailsResponse.strMeasure5,
                        strMeasure6 = mealDetailsResponse.strMeasure6,
                        strMeasure7 = mealDetailsResponse.strMeasure7,
                        strMeasure8 = mealDetailsResponse.strMeasure8,
                        strMeasure9 = mealDetailsResponse.strMeasure9,
                        strMeasure10 = mealDetailsResponse.strMeasure10,
                        strMeasure11 = mealDetailsResponse.strMeasure11,
                        strMeasure12 = mealDetailsResponse.strMeasure12,
                        strMeasure13 = mealDetailsResponse.strMeasure13,
                        strMeasure14 = mealDetailsResponse.strMeasure14,
                        strMeasure15 = mealDetailsResponse.strMeasure15,
                        strMeasure16 = mealDetailsResponse.strMeasure16,
                        strMeasure17 = mealDetailsResponse.strMeasure17,
                        strMeasure18 = mealDetailsResponse.strMeasure18,
                        strMeasure19 = mealDetailsResponse.strMeasure19,
                        strMeasure20 = mealDetailsResponse.strMeasure20,
                        strSource = mealDetailsResponse.strSource,
                        strImageSource = mealDetailsResponse.strImageSource,
                        strCreativeCommonsConfirmed = mealDetailsResponse.strCreativeCommonsConfirmed,
                        dateModified = mealDetailsResponse.dateModified,
                        strDrinkAlternate = mealDetailsResponse.strDrinkAlternate
                    )
                }
            }
    }

}