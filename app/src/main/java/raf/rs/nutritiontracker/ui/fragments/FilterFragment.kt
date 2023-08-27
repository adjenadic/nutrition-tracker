package raf.rs.nutritiontracker.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.adapters.AreasAdapter
import raf.rs.nutritiontracker.adapters.CategoriesAdapter
import raf.rs.nutritiontracker.adapters.IngredientsAdapter
import raf.rs.nutritiontracker.model.entities.Area
import raf.rs.nutritiontracker.model.entities.Category
import raf.rs.nutritiontracker.model.entities.Ingredient
import raf.rs.nutritiontracker.ui.contracts.MainContract
import raf.rs.nutritiontracker.ui.viewmodels.FilterViewModel

class FilterFragment : Fragment(R.layout.filter_recyclerview) {
    private val filterViewModel: MainContract.FilterViewModel by viewModel<FilterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var activePage = 1
        val query = ""
        val recyclerView: RecyclerView = view.findViewById(R.id.filterRV)
        val searchView: SearchView = view.findViewById(R.id.filterSearch)
        val buttonCategories: Button = view.findViewById(R.id.buttonCategories)
        val buttonAreas: Button = view.findViewById(R.id.buttonAreas)
        val buttonIngrs: Button = view.findViewById(R.id.buttonIngrs)
        val buttonSort: ToggleButton = view.findViewById(R.id.buttonSort)

        filterViewModel.getCategories()
        filterViewModel.getAreas()
        filterViewModel.getIngredients()

        super.onViewCreated(view, savedInstanceState)

        buttonCategories.setOnClickListener {
            buttonSort.isChecked = false
            filterViewModel.categories.observe(viewLifecycleOwner) { categories ->
                recyclerView.adapter = CategoriesAdapter(categories)
            }
            activePage = 1
        }

        buttonAreas.setOnClickListener {
            buttonSort.isChecked = false
            filterViewModel.areas.observe(viewLifecycleOwner) { areas ->
                recyclerView.adapter = AreasAdapter(areas)
            }
            activePage = 2
        }

        buttonIngrs.setOnClickListener {
            buttonSort.isChecked = false
            filterViewModel.ingredients.observe(viewLifecycleOwner) { ingredients ->
                recyclerView.adapter = IngredientsAdapter(ingredients)
            }
            activePage = 3
        }

        buttonSort.setOnCheckedChangeListener { _, _ ->
            filterViewModel.filterByCriteria(
                buttonSort.isChecked,
                query,
                activePage
            )
            when (activePage) {
                1 -> {
                    filterViewModel.filtered.observe(viewLifecycleOwner) { filtered ->
                        recyclerView.adapter =
                            CategoriesAdapter(filtered as List<Category>?)
                    }
                }

                2 -> {
                    filterViewModel.filtered.observe(viewLifecycleOwner) { filtered ->
                        recyclerView.adapter = AreasAdapter(filtered as List<Area>)
                    }
                }

                3 -> {
                    filterViewModel.filtered.observe(viewLifecycleOwner) { filtered ->
                        recyclerView.adapter =
                            IngredientsAdapter(filtered as List<Ingredient>)
                    }
                }
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                when (activePage) {
                    1 -> {
                        if (!query.isNullOrBlank()) {
                            filterViewModel.filterByCriteria(
                                buttonSort.isChecked,
                                query,
                                activePage
                            )
                            filterViewModel.filtered.observe(viewLifecycleOwner) { filtered ->
                                recyclerView.adapter = CategoriesAdapter(
                                    filtered as List<Category>?
                                )
                            }
                        } else {
                            filterViewModel.categories.observe(viewLifecycleOwner) { categories ->
                                recyclerView.adapter = CategoriesAdapter(categories)
                            }
                        }
                    }

                    2 -> {
                        if (!query.isNullOrBlank()) {
                            filterViewModel.filterByCriteria(
                                buttonSort.isChecked,
                                query,
                                activePage
                            )
                            filterViewModel.filtered.observe(viewLifecycleOwner) { filtered ->
                                recyclerView.adapter =
                                    AreasAdapter(filtered as List<Area>?)
                            }
                        } else {
                            filterViewModel.areas.observe(viewLifecycleOwner) { areas ->
                                recyclerView.adapter = AreasAdapter(areas)
                            }
                        }
                    }

                    3 -> {
                        if (!query.isNullOrBlank()) {
                            filterViewModel.filterByCriteria(
                                buttonSort.isChecked,
                                query,
                                activePage
                            )
                            filterViewModel.filtered.observe(viewLifecycleOwner) { filtered ->
                                recyclerView.adapter = IngredientsAdapter(
                                    filtered as List<Ingredient>?
                                )
                            }
                        } else {
                            filterViewModel.ingredients.observe(viewLifecycleOwner) { ingredients ->
                                recyclerView.adapter = IngredientsAdapter(ingredients)
                            }
                        }
                    }
                }
                return true
            }
        })

        filterViewModel.categories.observe(viewLifecycleOwner) {
            recyclerView.adapter = CategoriesAdapter(it)
        }

        recyclerView.layoutManager = LinearLayoutManager(view.context)
    }
}