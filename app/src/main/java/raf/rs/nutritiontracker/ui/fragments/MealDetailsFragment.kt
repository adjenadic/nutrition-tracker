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
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.model.entities.Meal
import raf.rs.nutritiontracker.model.entities.getIngredientAndMeasure
import raf.rs.nutritiontracker.ui.contracts.MainContract
import raf.rs.nutritiontracker.ui.viewmodels.MealDetailsViewModel
import raf.rs.nutritiontracker.ui.viewmodels.PlanViewModel

class MealDetailsFragment(private val id: Int, private val meal: Meal) :
    Fragment(R.layout.meal_details) {
    private val mealDetailsViewModel: MainContract.MealDetailsViewModel by viewModel<MealDetailsViewModel>()
    private val planViewModel: MainContract.PlanViewModel by viewModel<PlanViewModel>()

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
        mealDetailsViewModel.getMeal(id.toString())

        Handler(Looper.getMainLooper()).postDelayed({
            mealSaveButton.setOnClickListener {
                val fragment = MealSaveFragment(mealDetailsViewModel.meals.value?.get(0))
                parentFragmentManager.beginTransaction()
                    .replace(R.id.mainFL, fragment)
                    .addToBackStack(null)
                    .commit()
            }

            val frag = parentFragmentManager.findFragmentByTag("DailyPlanFragment")

            if (frag != null && frag.isInBackStack()) {
                mealSaveButton.setOnClickListener {
                    planViewModel.setSelectedMeal(meal)

                    if (frag is DailyPlanFragment) {
                        frag.selectedMeal = meal
                    }

                    parentFragmentManager.popBackStack(
                        "DailyPlanFragment",
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                    )
                }
            }

            mealDetailsStr.text = mealDetailsViewModel.meals.value?.get(0)?.strMeal
            mealDetailsTags.text = mealDetailsViewModel.meals.value?.get(0)?.strTags
            mealDetailsCategory.text = mealDetailsViewModel.meals.value?.get(0)?.strCategory
            mealDetailsArea.text = mealDetailsViewModel.meals.value?.get(0)?.strArea
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

            mealDetailsIngredients.text = formattedIngredients
            mealDetailsInstructions.text = mealDetailsViewModel.meals.value?.get(0)?.strInstructions

            val strYT =
                mealDetailsViewModel.meals.value?.get(0)?.strYoutube?.replace(
                    "/watch?v=",
                    "/embed/"
                )
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
                .load(mealDetailsViewModel.meals.value?.get(0)?.strMealThumb)
                .into(mealDetailsThumb)
        }, 150)
    }

    private fun Fragment.isInBackStack(): Boolean {
        val backStackCount = parentFragmentManager.backStackEntryCount
        for (i in 0 until backStackCount) {
            val backStackEntry = parentFragmentManager.getBackStackEntryAt(i)
            if (backStackEntry.name == tag) {
                return true
            }
        }
        return false
    }
}