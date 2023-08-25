package raf.rs.nutritiontracker.ui.viewholders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import raf.rs.nutritiontracker.R

class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val itemName: TextView = itemView.findViewById(R.id.itemName)
    val itemThumb: ImageView = itemView.findViewById(R.id.itemThumb)
    val itemMore: Button = itemView.findViewById(R.id.itemMore)
}