package raf.rs.nutritiontracker.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.adapters.MealsAdapter
import raf.rs.nutritiontracker.database.MealDBConverter
import raf.rs.nutritiontracker.model.entities.Meal
import raf.rs.nutritiontracker.ui.contracts.MainContract
import raf.rs.nutritiontracker.ui.viewmodels.MealDBViewModel
import raf.rs.nutritiontracker.ui.viewmodels.MealsViewModel

class MealsFragment(private val ids: String?, private val type: String) :
    Fragment(R.layout.meals_recyclerview) {
    private val mealsViewModel: MainContract.MealViewModel by viewModel<MealsViewModel>()
    private val dbViewModel: MainContract.DBViewModel by viewModel<MealDBViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.listRV)
        val searchView: SearchView = view.findViewById(R.id.menuSearchView)
        val savedButton: ToggleButton = view.findViewById(R.id.menuSavedButton)
        savedButton.isChecked = false

        selectType(type)
        mealsViewModel.meals.observe(viewLifecycleOwner) {
            recyclerView.adapter = MealsAdapter(it)
        }

        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(view.context)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    mealsViewModel.getMealsByName(query)
                } else {
                    mealsViewModel.getMeals(ids!!)
                }
                return true
            }
        })

        savedButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                dbViewModel.getAllMealsFromDB()
                dbViewModel.mealsDB.observe(viewLifecycleOwner) {
                    val list = it.map { mealEntity ->
                        MealDBConverter.toMeal(mealEntity)
                    }
                    recyclerView.adapter = MealsAdapter(list as List<Meal>, true)
                }
            } else {
                selectType(type)
                mealsViewModel.meals.observe(viewLifecycleOwner) {
                    recyclerView.adapter = MealsAdapter(it)
                }
            }
        }
    }

    private fun selectType(type: String) {
        when (type) {
            "Category" -> {
                mealsViewModel.getMeals(ids!!)
            }

            "Area" -> {
                mealsViewModel.getMealsByArea(ids!!)
            }

            "Ingredient" -> {
                mealsViewModel.getMealsByIngredient(ids!!)
            }
        }
    }
}

