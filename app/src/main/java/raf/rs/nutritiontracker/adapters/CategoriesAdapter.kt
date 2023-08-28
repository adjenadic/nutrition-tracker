package raf.rs.nutritiontracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.model.entities.Category
import raf.rs.nutritiontracker.ui.fragments.CategoryDetailsFragment
import raf.rs.nutritiontracker.ui.fragments.MealsFragment
import raf.rs.nutritiontracker.ui.viewholders.MenuViewHolder

class CategoriesAdapter(
    private val categories: List<Category>?,
) :
    RecyclerView.Adapter<MenuViewHolder>() {
    private lateinit var parent: ViewGroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        this.parent = parent
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item, parent, false)
        MenuViewHolder(view)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (categories != null)
            return categories.size
        return 0
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val category = categories?.get(position)
        if (category != null) {
            holder.itemMore.setOnClickListener {
                val fragment = CategoryDetailsFragment(
                    category
                )
                val fragmentManager = (parent.context as AppCompatActivity).supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.mainFL, fragment)
                fragmentTransaction.commit()
            }

            holder.itemThumb.setOnClickListener {
                val fragment = MealsFragment(
                    category.strCategory, "Category"
                )
                val fragmentManager = (parent.context as AppCompatActivity).supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.mainFL, fragment)
                fragmentTransaction.addToBackStack("CategoriesAdapter")
                fragmentTransaction.commit()
            }

            holder.itemName.text = category.strCategory
            Glide.with(holder.itemThumb.context)
                .load(category.strCategoryThumb)
                .into(holder.itemThumb)
        }
    }
}