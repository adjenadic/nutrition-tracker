package raf.rs.nutritiontracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.model.entities.Meal
import raf.rs.nutritiontracker.ui.fragments.MealDetailsFragment
import raf.rs.nutritiontracker.ui.viewholders.MenuViewHolder

class MealsAdapter(private val mList: List<Meal>?) :
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
        if (mList != null)
            return mList.size
        return 0
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val meal = mList?.get(position)
        if (meal != null) {
            holder.itemMore.setOnClickListener {
                val fragment = MealDetailsFragment(
                    meal.idMeal
                )
                val fragmentManager = (parent.context as AppCompatActivity).supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.mainFL, fragment)
                fragmentTransaction.commit()
            }

            holder.itemName.text = meal.strMeal
            Glide.with(holder.itemThumb.context)
                .load(meal.strMealThumb)
                .into(holder.itemThumb)
        }
    }
}