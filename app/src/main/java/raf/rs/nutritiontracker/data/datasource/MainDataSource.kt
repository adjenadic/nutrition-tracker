package raf.rs.nutritiontracker.data.datasource

import io.reactivex.Observable
import raf.rs.nutritiontracker.model.api.*
import retrofit2.http.GET
import retrofit2.http.Query

interface MainDataSource {
    @GET("https://www.themealdb.com/api/json/v1/1/categories.php")
    fun getAllCategories(): Observable<CategoriesResponse>

    @GET("https://www.themealdb.com/api/json/v1/1/list.php?i=list")
    fun getAllIngredients(): Observable<IngredientsResponse>

    @GET("https://www.themealdb.com/api/json/v1/1/list.php?a=list")
    fun getAllAreas(): Observable<AreasResponse>


    @GET("https://www.themealdb.com/api/json/v1/1/filter.php")
    fun getMealsByCategory(@Query("c") id: String): Observable<MealsResponse>


    @GET("https://www.themealdb.com/api/json/v1/1/filter.php?")
    fun getMealsByIngredient(@Query("i") id: String): Observable<MealsResponse>


    @GET("https://www.themealdb.com/api/json/v1/1/filter.php?")
    fun getMealsByArea(@Query("a") id: String): Observable<MealsResponse>

    @GET("https://www.themealdb.com/api/json/v1/1/lookup.php?")
    fun getMealDetails(@Query("i") id: String): Observable<MealsDetailsResponse>

    @GET("https://www.themealdb.com/api/json/v1/1/search.php?")
    fun getFilterByName(@Query("s") id: String): Observable<MealsResponse>
}