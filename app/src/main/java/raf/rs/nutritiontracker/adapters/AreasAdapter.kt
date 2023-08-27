package raf.rs.nutritiontracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import raf.rs.nutritiontracker.R
import raf.rs.nutritiontracker.model.entities.Area
import raf.rs.nutritiontracker.ui.fragments.MealsFragment
import raf.rs.nutritiontracker.ui.viewholders.MenuViewHolder

class AreasAdapter(private val areas: List<Area>?) :
    RecyclerView.Adapter<MenuViewHolder>() {
    private lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        this.parent = parent
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return areas?.size ?: 0
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val area = areas?.get(position)
        if (area != null) {
            holder.itemThumb.setOnClickListener {
                val fragment = MealsFragment(
                    area.strArea, "Area"
                )
                val fragmentManager = (parent.context as AppCompatActivity).supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.mainFL, fragment)
                fragmentTransaction.commit()
            }

            holder.itemName.text = area.strArea
//            Glide.with(holder.itemThumb.context)
//                .load(area.)
//                .into(holder.itemThumb)
        }
    }
}
