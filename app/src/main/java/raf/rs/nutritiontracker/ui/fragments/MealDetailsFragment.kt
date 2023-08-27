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
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.model.entities.getIngredientAndMeasure
import raf.rs.nutritiontracker.ui.contracts.MainContract
import raf.rs.nutritiontracker.ui.viewmodels.MealDetailsViewModel

class MealDetailsFragment(private val id: Int) : Fragment(R.layout.meal_details) {
    private val detailedMealModel: MainContract.DetailedMealModel by viewModel<MealDetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mealDetailsThumb = view.findViewById<ImageView>(R.id.mealDetailsThumb)
        val mealSaveButton = view.findViewById<Button>(R.id.saveButton)
        val mealDetailsStr = view.findViewById<TextView>(R.id.mealDetailsStr)
        val mealDetailsTags = view.findViewById<TextView>(R.id.mealDetailsTags)
        val mealDetailsCategory = view.findViewById<TextView>(R.id.mealDetailsCategory)
        val mealDetailsArea = view.findViewById<TextView>(R.id.mealDetailsArea)
        val mealDetailsIngredients = view.findViewById<TextView>(R.id.mealDetailsIng)
        val mealDetailsInstructions = view.findViewById<TextView>(R.id.mealDetailsInstructions)
        val mealDetailsWebView = view.findViewById<WebView>(R.id.mealDetailsYT)
        detailedMealModel.getMeal(id.toString())

        Handler(Looper.getMainLooper()).postDelayed({

            mealSaveButton.setOnClickListener {
                val fragment = MealSaveFragment(detailedMealModel.meals.value?.get(0))
                parentFragmentManager.beginTransaction()
                    .replace(R.id.mainFL, fragment)
                    .addToBackStack(null)
                    .commit()
            }

            mealDetailsStr.text = detailedMealModel.meals.value?.get(0)?.strMeal
            mealDetailsTags.text = detailedMealModel.meals.value?.get(0)?.strTags
            mealDetailsCategory.text = detailedMealModel.meals.value?.get(0)?.strCategory
            mealDetailsArea.text = detailedMealModel.meals.value?.get(0)?.strArea
            val detailedMeal = detailedMealModel.meals.value?.get(0)
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

            mealDetailsIngredients.text = formattedIngredients
            mealDetailsInstructions.text = detailedMealModel.meals.value?.get(0)?.strInstructions

            val strYT =
                detailedMealModel.meals.value?.get(0)?.strYoutube?.replace("/watch?v=", "/embed/")
            mealDetailsWebView.loadDataWithBaseURL(
                null,
                """<html><head><style type="text/css">body{margin:0;padding:0;overflow:hidden;}</style></head><body><iframe width="100%" height="100%" src="$strYT" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe></body></html>""",
                "text/html",
                "UTF-8",
                null
            )
            mealDetailsWebView.settings.javaScriptEnabled = true
            mealDetailsWebView.webChromeClient = WebChromeClient()

            Glide.with(mealDetailsThumb.context)
                .load(detailedMealModel.meals.value?.get(0)?.strMealThumb)
                .into(mealDetailsThumb)
        }, 150)
    }
}