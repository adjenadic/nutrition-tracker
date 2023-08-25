package raf.rs.nutritiontracker.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.model.entities.Category
import raf.rs.nutritiontracker.ui.contracts.MainContract
import raf.rs.nutritiontracker.ui.viewmodels.CategoriesViewModel

class CategoryDetailsFragment(private val category: Category) :
    Fragment(R.layout.category_details) {
    private val categoriesViewModel: MainContract.CategoryViewModel by viewModel<CategoriesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        categoriesViewModel.getCategories()

        super.onViewCreated(view, savedInstanceState)

        val categoryDetailsThumb = view.findViewById<ImageView>(R.id.catDetailsThumb)
        val categoryDetailsStr = view.findViewById<TextView>(R.id.catDetailsStr)
        val categoryDetailsDesc = view.findViewById<TextView>(R.id.catDetailsDrinkAlt)

        Glide.with(categoryDetailsThumb.context)
            .load(category.strCategoryThumb)
            .into(categoryDetailsThumb)

        categoryDetailsStr.text = category.strCategory
        categoryDetailsDesc.text = category.strCategoryDescription
    }
}