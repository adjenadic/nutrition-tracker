package raf.rs.nutritiontracker.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.model.entities.Meal
import raf.rs.nutritiontracker.model.entities.getIngredientAndMeasure
import raf.rs.nutritiontracker.ui.contracts.MainContract
import raf.rs.nutritiontracker.ui.viewmodels.MealDBViewModel
import raf.rs.nutritiontracker.ui.viewmodels.MealDetailsViewModel

class MealDBDetailsFragment(private val meal: Meal) : Fragment(R.layout.meal_saved_details) {
    private val mealDBViewModel: MainContract.DBViewModel by viewModel<MealDBViewModel>()
    private val mealDetailsViewModel: MainContract.MealDetailsViewModel by viewModel<MealDetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mealSavedThumb = view.findViewById<ImageView>(R.id.mealSavedThumb)
        val mealEditButton = view.findViewById<Button>(R.id.editButton)
        val mealDeleteButton = view.findViewById<Button>(R.id.deleteButton)
        val mealSavedStr = view.findViewById<TextView>(R.id.mealSavedStr)
        val mealSavedTags = view.findViewById<TextView>(R.id.mealSavedTags)
        val mealSavedCategory = view.findViewById<TextView>(R.id.mealSavedCategory)
        val mealSavedArea = view.findViewById<TextView>(R.id.mealSavedArea)
        val mealSavedIngredients = view.findViewById<TextView>(R.id.mealSavedIng)
        val mealSavedInstructions = view.findViewById<TextView>(R.id.mealSavedInstructions)
        val mealSavedWebView = view.findViewById<WebView>(R.id.mealSavedYT)
        mealDBViewModel.getMealByIDFromDB(meal.idMeal)
        mealDetailsViewModel.getMeal(meal.idMeal.toString())

        Handler(Looper.getMainLooper()).postDelayed({
            mealEditButton.setOnClickListener {
                val fragment = MealSaveFragment(mealDetailsViewModel.meals.value?.get(0), true)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.mainFL, fragment)
                    .addToBackStack(null)
                    .commit()
            }
            mealDeleteButton.setOnClickListener {
                mealDBViewModel.deleteMealByIDFromDB(
                    mealDBViewModel.mealDB.value!!.idMeal,
                    object : MainContract.MealDBCallback {
                        override fun onMealSuccess() {
                            Toast.makeText(view.context, "Meal deleted.", Toast.LENGTH_SHORT).show()
                            parentFragmentManager.popBackStack(
                                "MealsAdapter",
                                FragmentManager.POP_BACK_STACK_INCLUSIVE
                            )
                        }

                        override fun onMealError(error: Throwable) {
                            Toast.makeText(view.context, "Meal not deleted.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
            }

            mealSavedStr.text = mealDetailsViewModel.meals.value?.get(0)?.strMeal
            mealSavedTags.text = mealDetailsViewModel.meals.value?.get(0)?.strTags
            mealSavedCategory.text = mealDetailsViewModel.meals.value?.get(0)?.strCategory
            mealSavedArea.text = mealDetailsViewModel.meals.value?.get(0)?.strArea
            val detailedMeal = mealDetailsViewModel.meals.value?.get(0)
            val ingredientsMap = mutableMapOf<String, String?>()

            if (detailedMeal != null) {
                for (i in 1..20) {
                    val (ingredient, measure) = getIngredientAndMeasure(detailedMeal, i)

                    if (!ingredient.isNullOrBlank()) {
                        ingredientsMap[ingredient] = measure
                    }
                }
            }

            val formattedIngredients =
                ingredientsMap.entries.joinToString("\n") { (ingredient, measure) ->
                    if (measure.isNullOrBlank()) {
                        ingredient
                    } else {
                        "$ingredient - $measure"
                    }
                }

            mealSavedIngredients.text = formattedIngredients
            mealSavedInstructions.text = mealDetailsViewModel.meals.value?.get(0)?.strInstructions

            val strYT =
                mealDetailsViewModel.meals.value?.get(0)?.strYoutube?.replace(
                    "/watch?v=",
                    "/embed/"
                )
            mealSavedWebView.loadDataWithBaseURL(
                null,
                """<html><head><style type="text/css">body{margin:0;padding:0;overflow:hidden;}</style></head><body><iframe width="100%" height="100%" src="$strYT" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe></body></html>""",
                "text/html",
                "UTF-8",
                null
            )
            mealSavedWebView.settings.javaScriptEnabled = true
            mealSavedWebView.webChromeClient = WebChromeClient()

            Glide.with(mealSavedThumb.context)
                .load(mealDBViewModel.mealDB.value!!.strMealThumb)
                .into(mealSavedThumb)
        }, 150)
    }
}