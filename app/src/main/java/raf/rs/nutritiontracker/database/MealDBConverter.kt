package raf.rs.nutritiontracker.database

import raf.rs.nutritiontracker.model.entities.Meal
import raf.rs.nutritiontracker.model.entities.MealDBEntity
import raf.rs.nutritiontracker.model.entities.MealDetails
import java.util.Date

class MealDBConverter {
    companion object {
        fun toMealEntity(value: MealDetails?): MealDBEntity? {
            if (value == null) {
                return null
            }

            return try {
                MealDBEntity(
                    value.idMeal.toInt(),
                    value.strMeal,
                    value.strDrinkAlternate,
                    value.strCategory,
                    value.strArea,
                    value.strInstructions,
                    value.strMealThumb,
                    value.strTags,
                    value.strYoutube,
                    value.strIngredient1,
                    value.strIngredient2,
                    value.strIngredient3,
                    value.strIngredient4,
                    value.strIngredient5,
                    value.strIngredient6,
                    value.strIngredient7,
                    value.strIngredient8,
                    value.strIngredient9,
                    value.strIngredient10,
                    value.strIngredient11,
                    value.strIngredient12,
                    value.strIngredient13,
                    value.strIngredient14,
                    value.strIngredient15,
                    value.strIngredient16,
                    value.strIngredient17,
                    value.strIngredient18,
                    value.strIngredient19,
                    value.strIngredient20,
                    value.strMeasure1,
                    value.strMeasure2,
                    value.strMeasure3,
                    value.strMeasure4,
                    value.strMeasure5,
                    value.strMeasure6,
                    value.strMeasure7,
                    value.strMeasure8,
                    value.strMeasure9,
                    value.strMeasure10,
                    value.strMeasure11,
                    value.strMeasure12,
                    value.strMeasure13,
                    value.strMeasure14,
                    value.strMeasure15,
                    value.strMeasure16,
                    value.strMeasure17,
                    value.strMeasure18,
                    value.strMeasure19,
                    value.strMeasure20,
                    value.strSource,
                    value.strImageSource,
                    value.strCreativeCommonsConfirmed,
                    value.dateModified?.toLong(),
                    Date().toString(),
                    "Breakfast"
                )
            } catch (e: Exception) {
                throw IllegalArgumentException(
                    "Error: MealDetails into MealEntity not converted",
                    e
                )
            }
        }

        fun toMeal(value: MealDBEntity?): Meal? {
            if (value == null) {
                return null
            }

            return try {
                Meal(
                    value.idMeal,
                    value.strMeal,
                    value.strMealThumb
                )
            } catch (e: Exception) {
                throw IllegalArgumentException(
                    "Error: MealEntity into Meal not converted",
                    e
                )
            }
        }
    }
}