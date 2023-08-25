package raf.rs.nutritiontracker.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.adapters.CategoriesAdapter
import raf.rs.nutritiontracker.ui.contracts.MainContract
import raf.rs.nutritiontracker.ui.viewmodels.CategoriesViewModel

class CategoriesFragment : Fragment(R.layout.categories_recyclerview) {
    private val categoriesViewModel: MainContract.CategoryViewModel by viewModel<CategoriesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView: RecyclerView = view.findViewById(R.id.listRV)

        categoriesViewModel.getCategories()

        super.onViewCreated(view, savedInstanceState)

        categoriesViewModel.categories.observe(viewLifecycleOwner) {
            recyclerView.adapter = CategoriesAdapter(it)
        }

        recyclerView.layoutManager = LinearLayoutManager(view.context)
    }
}